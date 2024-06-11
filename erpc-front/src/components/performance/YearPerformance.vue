<template>
    <div class="target-list-content">
        <div class="target-list">
            <h1>실적 조회</h1>
        </div>
        <div class="filters">
            <div class="year-filter">
                <select id="year-select" class="year-select" v-model="selectedYear" @change="handleYearChange">
                    <option v-for="year in uniqueYears" :key="year" :value="year">{{ year }}</option>
                </select>
            </div>
            <div class="team-filter">
                <select id="team-select" class="team-select" v-model="selectedTeam" @change="handleTeamChange"
                    :disabled="isEmployeeSearchActive || searchQuery.length > 0">
                    <option value="">선택안함</option>
                    <option value="1">영업1팀</option>
                    <option value="2">영업2팀</option>
                    <option value="3">영업3팀</option>
                    <option value="4">영업4팀</option>
                </select>
            </div>
            <div class="search-box">
                <input type="text" class="search-input" v-model="searchQuery" placeholder="사원명으로 조회"
                    @input="handleInput">
                <button class="search-btn" @click="fetchEmployeeData">조회하기</button>
            </div>
        </div>
        <div class="target-list-box">
            <table class="target-table" v-if="filteredTargetData.length">
                <thead>
                    <tr>
                        <th>연도</th>
                        <th>월/분기</th>
                        <th>목표 금액</th>
                        <th>달성 금액</th>
                        <th class="filter-header">
                            <span>달성 필요금액</span>
                            <button class="filter-btn" @click="toggleFilter">{{ showAll ? '전체' : '달성초과' }}</button>
                        </th>
                        <th>달성률</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(target, index) in filteredTargetData" :key="index"
                        :class="{ 'quarter-row': isQuarterRow(target.displayMonthOrQuarter), 'total-row': isTotalRow(target.displayMonthOrQuarter) }">
                        <td>{{ target.year }}</td>
                        <td>{{ target.displayMonthOrQuarter }}</td>
                        <td>{{ formatNumber(target.goal) }}</td>
                        <td>{{ formatNumber(getAchievementValue(target.year, target.displayMonthOrQuarter)) }}</td>
                        <td
                            :class="{ 'positive': getRequiredValue(target.goal, getAchievementValue(target.year, target.displayMonthOrQuarter)) > 0 }">
                            {{ formatNumber(getRequiredValue(target.goal, getAchievementValue(target.year,
                                target.displayMonthOrQuarter))) }}
                        </td>

                        <td>{{ getPercentage(target.goal, getAchievementValue(target.year,
                            target.displayMonthOrQuarter)) }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import axios from 'axios';

const targetData = ref([]);
const filteredTargetData = ref([]);
const selectedYear = ref('2024');  // 기본값을 2024로 설정
const selectedTeam = ref('');  // 팀 필터 추가
const uniqueYears = ref([]);
const searchQuery = ref(''); // 검색어를 저장할 변수
const isEmployeeSearchActive = ref(false); // 사원명 검색 활성화 상태를 저장할 변수
const employeeName = ref(''); // 조회된 사원명을 저장할 변수
const salesData = ref({}); // 실적 데이터를 저장할 변수
const teamSalesData = ref({}); // 팀 실적 데이터를 저장할 변수
const employeeSalesData = ref({}); // 사원 실적 데이터를 저장할 변수
const showAll = ref(true); // 전체/달성초과 토글 상태

const fetchSalesData = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales');
        salesData.value = response.data.sales;
    } catch (error) {
        console.error('Error fetching sales data:', error);
        alert('실적 데이터를 조회하는 중 오류가 발생했습니다.');
    }
};

const fetchTeamSalesData = async (teamCodeId) => {
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales/team/${teamCodeId}`);
        teamSalesData.value = response.data.sales;
    } catch (error) {
        console.error('Error fetching team sales data:', error);
        alert('팀 실적 데이터를 조회하는 중 오류가 발생했습니다.');
    }
};

const fetchEmployeeSalesData = async (employeeId) => {
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales/employee/${employeeId}`);
        employeeSalesData.value = response.data.sales;
    } catch (error) {
        console.error('Error fetching employee sales data:', error);
        alert('사원 실적 데이터를 조회하는 중 오류가 발생했습니다.');
    }
};

const getAchievementValue = (year, displayMonthOrQuarter) => {
    let yearData;

    if (isEmployeeSearchActive.value && searchQuery.value) {
        yearData = employeeSalesData.value[year];
    } else if (selectedTeam.value) {
        yearData = teamSalesData.value[year];
    } else {
        yearData = salesData.value[year];
    }

    if (!yearData) return 0;

    if (displayMonthOrQuarter.includes('월')) {
        const month = displayMonthOrQuarter.replace('월', '').trim().padStart(2, '0');
        return yearData[month] || 0;
    }

    if (displayMonthOrQuarter.includes('분기')) {
        const quarter = displayMonthOrQuarter.replace('분기', '').trim();
        const months = {
            '1': ['01', '02', '03'],
            '2': ['04', '05', '06'],
            '3': ['07', '08', '09'],
            '4': ['10', '11', '12']
        };
        return months[quarter].reduce((sum, month) => sum + (yearData[month] || 0), 0);
    }

    if (displayMonthOrQuarter === '총계') {
        return yearData.total || 0;
    }

    return 0;
};

const getRequiredValue = (goal, achievement) => {
    return goal - achievement;
};

const getPercentage = (goal, achievement) => {
    return ((achievement / goal) * 100).toFixed(1) + '%';
};

const formatNumber = (num) => {
    if (!num && num !== 0) return '';  // undefined 또는 null 체크
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

const processTargetData = (data) => {
    const order = [
        { targetMonth: 1, display: '1월', type: 'month' },
        { targetMonth: 2, display: '2월', type: 'month' },
        { targetMonth: 3, display: '3월', type: 'month' },
        { targetQuarter: 1, display: '1분기', type: 'quarter' },
        { targetMonth: 4, display: '4월', type: 'month' },
        { targetMonth: 5, display: '5월', type: 'month' },
        { targetMonth: 6, display: '6월', type: 'month' },
        { targetQuarter: 2, display: '2분기', type: 'quarter' },
        { targetMonth: 7, display: '7월', type: 'month' },
        { targetMonth: 8, display: '8월', type: 'month' },
        { targetMonth: 9, display: '9월', type: 'month' },
        { targetQuarter: 3, display: '3분기', type: 'quarter' },
        { targetMonth: 10, display: '10월', type: 'month' },
        { targetMonth: 11, display: '11월', type: 'month' },
        { targetMonth: 12, display: '12월', type: 'month' },
        { targetQuarter: 4, display: '4분기', type: 'quarter' },
        { targetMonth: null, display: '총계', type: 'total' }
    ];

    const yearDataMap = {};

    data.forEach(entry => {
        const year = entry.targetYear;
        const month = parseInt(entry.targetMonth);
        const quarter = parseInt(entry.targetQuarter);

        if (!yearDataMap[year]) {
            yearDataMap[year] = {};
        }

        if (month) {
            yearDataMap[year][`month-${month}`] = {
                year,
                displayMonthOrQuarter: `${month}월`,
                goal: parseInt(entry.targetPrice),
                grade: entry.grade || 0,
                require: entry.require || 0,
                percent: entry.percent || 0,
                teamCodeId: entry.team?.teamCodeId || entry.employee?.teamCode?.teamCodeId || null,
                employeeName: entry.employee?.employeeName || null
            };
        } else if (quarter) {
            yearDataMap[year][`quarter-${quarter}`] = {
                year,
                displayMonthOrQuarter: `${quarter}분기`,
                goal: parseInt(entry.targetPrice),
                grade: entry.grade || 0,
                require: entry.require || 0,
                percent: entry.percent || 0,
                teamCodeId: entry.team?.teamCodeId || entry.employee?.teamCode?.teamCodeId || null,
                employeeName: entry.employee?.employeeName || null
            };
        } else {
            yearDataMap[year]['total'] = {
                year,
                displayMonthOrQuarter: '총계',
                goal: parseInt(entry.targetPrice),
                grade: entry.grade || 0,
                require: entry.require || 0,
                percent: entry.percent || 0,
                teamCodeId: entry.team?.teamCodeId || entry.employee?.teamCode?.teamCodeId || null,
                employeeName: entry.employee?.employeeName || null
            };
        }
    });

    const processedData = [];
    for (const year of Object.keys(yearDataMap).sort((a, b) => b - a)) {
        for (const item of order) {
            const key = item.targetMonth ? `month-${item.targetMonth}` : item.targetQuarter ? `quarter-${item.targetQuarter}` : 'total';
            if (yearDataMap[year][key]) {
                processedData.push({ ...yearDataMap[year][key], periodType: item.type });
            }
        }
    }
    return processedData;
};

const fetchTargetData = async () => {
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/target/integrate`);
        const data = response.data;
        targetData.value = processTargetData(data);

        uniqueYears.value = [...new Set(targetData.value.map(item => item.year))].sort((a, b) => b - a);

        filterData();
    } catch (error) {
        console.error('Error fetching target data:', error);
        alert('실적 정보를 조회하는 중 오류가 발생했습니다.');
    }
};

const fetchTeamData = async (teamCodeId) => {
    try {
        await fetchTeamSalesData(teamCodeId);
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/target/team/${teamCodeId}`);
        const data = response.data;
        targetData.value = processTargetData(data);

        filterData();
    } catch (error) {
        console.error('Error fetching team data:', error);
        alert('팀 실적 정보를 조회하는 중 오류가 발생했습니다.');
    }
};

const fetchEmployeeData = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees');
        const employees = response.data;

        const matchingEmployee = employees.find(employee => employee.employeeName === searchQuery.value);
        if (matchingEmployee) {
            console.log(`Employee Id: ${matchingEmployee.employeeId}, Employee Name: ${matchingEmployee.employeeName}`);
            employeeName.value = matchingEmployee.employeeName; // 사원명을 저장
            isEmployeeSearchActive.value = true; // 사원명 검색 활성화
            selectedTeam.value = ''; // 팀 필터 초기화
            await fetchEmployeeSalesData(matchingEmployee.employeeId);
            const employeeResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/target/employee/${matchingEmployee.employeeId}`);
            targetData.value = processTargetData(employeeResponse.data);
            filterData();
        } else {
            console.log('No matching employee found');
            isEmployeeSearchActive.value = false; // 사원명 검색 비활성화
            employeeName.value = ''; // 사원명 초기화
        }
    } catch (error) {
        console.error('Error fetching employee data:', error);
        isEmployeeSearchActive.value = false; // 사원명 검색 비활성화
        employeeName.value = ''; // 사원명 초기화
    }
};

const handleInput = () => {
    if (searchQuery.value.length > 0) {
        isEmployeeSearchActive.value = true;
        selectedTeam.value = ''; // 팀 필터 초기화
    } else {
        isEmployeeSearchActive.value = false;
        employeeName.value = ''; // 사원명 초기화
        filterData();
    }
};

const isQuarterRow = (monthOrQuarter) => {
    return ['1분기', '2분기', '3분기', '4분기'].includes(monthOrQuarter);
};

const isTotalRow = (monthOrQuarter) => {
    return monthOrQuarter === '총계';
};

const filterData = () => {
    console.log('Filtering data...');
    let filteredData = targetData.value;

    if (selectedYear.value) {
        filteredData = filteredData.filter(target => target.year === selectedYear.value);
    }
    if (selectedTeam.value) {
        filteredData = filteredData.filter(target => target.teamCodeId == selectedTeam.value);
    }
    if (searchQuery.value) {
        filteredData = filteredData.filter(target => target.employeeName && target.employeeName.includes(searchQuery.value));
    }
    if (!showAll.value) {
        filteredData = filteredData.filter(target => getRequiredValue(target.goal, getAchievementValue(target.year, target.displayMonthOrQuarter)) < 0);
    }
    console.log('Filtered target data:', filteredData);
    filteredTargetData.value = filteredData;
};

const handleYearChange = () => {
    if (!isEmployeeSearchActive.value) {
        searchQuery.value = '';  // Reset search query
        if (selectedTeam.value) {
            fetchTeamData(selectedTeam.value);
        } else {
            fetchTargetData();
        }
    }
};

const handleTeamChange = () => {
    if (!isEmployeeSearchActive.value) {
        searchQuery.value = '';  // Reset search query
        if (!selectedTeam.value) {
            fetchTargetData();  // Fetch year data
        } else {
            fetchTeams();
        }
    }
};

const fetchTeams = async () => {
    const teamCodeId = selectedTeam.value;
    if (teamCodeId) {
        await fetchTeamData(teamCodeId);
    } else {
        filterData();
    }
};

const toggleFilter = () => {
    showAll.value = !showAll.value;
    filterData();
};

onMounted(async () => {
    await fetchSalesData();
    await fetchTargetData();
});

watch([selectedYear, selectedTeam, searchQuery], () => {
    filterData();
});
</script>

<style>
    @import url('@/assets/css/performance/YearPerformance.css');
</style>
