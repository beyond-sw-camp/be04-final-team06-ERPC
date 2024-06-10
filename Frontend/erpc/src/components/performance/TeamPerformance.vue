<template>
    <div class="team-list-content">
        <div class="team-list">
            <h1>Team 실적 조회</h1>
        </div>
        <div class="team-list-search">
            <div class="team-dropdown">
                <button class="team-dropdown-btn">{{ searchBy }} ▼</button>
                <div class="team-dropdown-content">
                    <a href="#" @click="setSearchBy('영업1팀')">영업1팀</a>
                    <a href="#" @click="setSearchBy('영업2팀')">영업2팀</a>
                    <a href="#" @click="setSearchBy('영업3팀')">영업3팀</a>
                </div>
            </div>
            <div class="team-search-box">
                <input type="text" class="team-search-input" v-model="searchQuery" placeholder="검색어를 입력하세요">
                <button class="team-search-btn" @click="applyFilter">실적 조회</button>
            </div>
        </div>
        <div class="team-list-box">
            <table class="team-table" v-if="teams.length > 0">
                <thead>
                    <tr>
                        <th>연도</th>
                        <th>월/분기</th>
                        <th>{{ teamName }} 목표 금액</th>
                        <th>{{ teamName }} 달성 금액</th>
                        <th>{{ teamName }} 달성 필요금액</th>
                        <th>{{ teamName }} 달성률</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(team, index) in filteredTeams" :key="index"
                        :class="{ 'quarter-row': isQuarterRow(team.month), 'total-row': isTotalRow(team.month) }">
                        <td>{{ team.year }}</td>
                        <td>{{ team.displayMonthOrQuarter }}</td>
                        <td>{{ team.goal }}</td>
                        <td>{{ team.grade }}</td>
                        <td>{{ team.require }}</td>
                        <td>{{ team.percent }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const teams = ref([]);
const searchQuery = ref('');
const searchBy = ref('Team');
const filteredTeams = ref([]);
const teamName = ref('');

const fetchTeamData = async (teamId) => {
    try {
        const response = await axios.get(`http://localhost:7775/target/team/${teamId}`);
        const data = response.data;
        if (data.length > 0) {
            teamName.value = data[0].team.teamCode;
        }

        teams.value = data.map(item => ({
            year: item.targetYear,
            displayMonthOrQuarter: item.targetQuarter ? `${item.targetQuarter}분기` : item.targetMonth ? `${item.targetMonth}월` : '총계',
            month: item.targetMonth ? `${item.targetYear}${item.targetMonth}` : item.targetQuarter ? `${item.targetYear}${item.targetQuarter}분기` : '총계',
            goal: item.targetPrice.toLocaleString(),
            grade: 'N/A', // 달성 금액 데이터가 없어서 임시로 'N/A'로 설정
            require: 'N/A', // 필요 금액 데이터가 없어서 임시로 'N/A'로 설정
            percent: 'N/A' // 달성률 데이터가 없어서 임시로 'N/A'로 설정
        }));
        applyFilter(); // 필터링 업데이트
    } catch (error) {
        console.error('Error fetching team data:', error);
        alert('팀 정보를 조회하는 중 오류가 발생했습니다.');
    }
};

const setSearchBy = (criteria) => {
    searchBy.value = criteria;
    let teamId;
    if (criteria === '영업1팀') {
        teamId = 1;
    } else if (criteria === '영업2팀') {
        teamId = 2;
    } else if (criteria === '영업3팀') {
        teamId = 3;
    }
    fetchTeamData(teamId);
};

const applyFilter = () => {
    filteredTeams.value = teams.value.filter(team =>
        team.month.includes(searchQuery.value)
    );
};

const isQuarterRow = (month) => {
    return ['1분기', '2분기', '3분기', '4분기'].includes(month);
};

const isTotalRow = (month) => {
    return month === '총계';
};
</script>

<style>
    @import url('@/assets/css/performance/TeamPerformance.css');
</style>
