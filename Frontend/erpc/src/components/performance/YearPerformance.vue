<template>
    <div class="year-list-content">
        <div class="year-list">
            <h1>연간 실적</h1>
        </div>
        <div class="year-list-search">
            <div class="year-dropdown">
                <button class="year-dropdown-btn">{{ searchBy }} ▼</button>
                <div class="year-dropdown-content">
                    <a href="#" @click="setSearchBy('2024')">2024</a>
                    <a href="#" @click="setSearchBy('2023')">2023</a>
                    <a href="#" @click="setSearchBy('2022')">2022</a>
                </div>
            </div>
        </div>
        <div class="year-list-box">
            <table class="year-table">
                <thead>
                    <tr>
                        <th>연월</th>
                        <th>목표 금액</th>
                        <th>총 달성 금액</th>
                        <th>영업1팀 기여 금액</th>
                        <th>영업2팀 기여 금액</th>
                        <th>영업3팀 기여 금액</th>
                        <th>달성률</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(year, index) in filteredyears" :key="index" :class="{'quarter-row': isQuarterRow(year.month), 'total-row': isTotalRow(year.month)}">
                        <td>{{ year.month }}</td>
                        <td>{{ year.goal }}</td>
                        <td>{{ year.grade }}</td>
                        <td>{{ year.salesteam1 }}</td>
                        <td>{{ year.salesteam2 }}</td>
                        <td>{{ year.salesteam3 }}</td>
                        <td>{{ year.percent }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import {useRoute, useRouter} from 'vue-router'

const currentRoute = useRoute();
const router = useRouter();

const years = ref([
    { month: '202301', goal: '1,000,000,000', grade: '900,000,000', salesteam1: '400,000,000', salesteam2: '300,000,000', salesteam3: '200,000,000', percent: '90%' },
    { month: '202302', goal: '1,000,000,000', grade: '800,000,000', salesteam1: '400,000,000', salesteam2: '200,000,000', salesteam3: '200,000,000', percent: '80%' },
    { month: '202303', goal: '1,000,000,000', grade: '1,200,000,000', salesteam1: '300,000,000', salesteam2: '500,000,000', salesteam3: '400,000,000', percent: '120%' },
    { month: '1분기', goal: '3,000,000,000', grade: '2,900,000,000', salesteam1: '1,100,000,000', salesteam2: '1,000,000,000', salesteam3: '800,000,000', percent: '96.67%' },

    { month: '202304', goal: '1,000,000,000', grade: '1,100,000,000', salesteam1: '500,000,000', salesteam2: '300,000,000', salesteam3: '300,000,000', percent: '110%' },
    { month: '202305', goal: '1,000,000,000', grade: '700,000,000', salesteam1: '200,000,000', salesteam2: '300,000,000', salesteam3: '400,000,000', percent: '70%' },
    { month: '202306', goal: '1,500,000,000', grade: '1,600,000,000', salesteam1: '600,000,000', salesteam2: '500,000,000', salesteam3: '500,000,000', percent: '106.67%' },
    { month: '2분기', goal: '3,500,000,000', grade: '3,400,000,000', salesteam1: '1,300,000,000', salesteam2: '1,100,000,000', salesteam3: '1,200,000,000', percent: '97.14%' },

    { month: '202307', goal: '2,000,000,000', grade: '2,000,000,000', salesteam1: '800,000,000', salesteam2: '700,000,000', salesteam3: '500,000,000', percent: '100%' },
    { month: '202308', goal: '2,000,000,000', grade: '2,700,000,000', salesteam1: '900,000,000', salesteam2: '900,000,000', salesteam3: '900,000,000', percent: '135%' },
    { month: '202309', goal: '1,500,000,000', grade: '1,300,000,000', salesteam1: '600,000,000', salesteam2: '400,000,000', salesteam3: '300,000,000', percent: '86.67%' },
    { month: '3분기', goal: '5,500,000,000', grade: '6,000,000,000', salesteam1: '2,300,000,000', salesteam2: '2,000,000,000', salesteam3: '1,700,000,000', percent: '109.09%' },

    { month: '202310', goal: '1,500,000,000', grade: '1,700,000,000', salesteam1: '600,000,000', salesteam2: '500,000,000', salesteam3: '600,000,000', percent: '113.33%' },
    { month: '202311', goal: '2,000,000,000', grade: '1,700,000,000', salesteam1: '500,000,000', salesteam2: '600,000,000', salesteam3: '600,000,000', percent: '85%' },
    { month: '202312', goal: '2,000,000,000', grade: '2,300,000,000', salesteam1: '1,000,000,000', salesteam2: '700,000,000', salesteam3: '600,000,000', percent: '115%' },
    { month: '4분기', goal: '5,500,000,000', grade: '5,700,000,000', salesteam1: '2,100,000,000', salesteam2: '1,800,000,000', salesteam3: '1,800,000,000', percent: '103.64%' },

    { month: '총계', goal: '17,500,000,000 ', grade: '17,000,000,000 ', salesteam1: '6,800,000,000', salesteam2: '5,900,000,000', salesteam3: '5,500,000,000', percent: '97.14%' }
]);
const searchQuery = ref('');
const searchBy = ref('연도');
const filteredyears = ref(years.value);

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function isQuarterRow(month) {
    return ['1분기', '2분기', '3분기', '4분기'].includes(month);
}

function isTotalRow(month) {
    return month === '총계';
}
</script>



<style>
    @import url('@/assets/css/performance/YearPerformance.css');
</style>
