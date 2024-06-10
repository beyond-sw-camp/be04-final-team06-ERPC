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
            <table class="team-table">
                <thead>
                    <tr>
                        <th>연월</th>
                        <th>영업1팀 목표 금액</th>
                        <th>영업1팀 달성 금액</th>
                        <th>영업1팀 달성 필요금액</th>
                        <th>영업1팀 달성률</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(team, index) in filteredteams" :key="index"
                        :class="{ 'quarter-row': isQuarterRow(team.month), 'total-row': isTotalRow(team.month) }">
                        <td>{{ team.month }}</td>
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
import {useRoute, useRouter} from 'vue-router'

const currentRoute = useRoute();
const router = useRouter();

const teams = ref([
    { month: '202301', goal: '400,000,000', grade: '400,000,000', require: '0', percent: '100%' },
    { month: '202302', goal: '400,000,000', grade: '400,000,000', require: '0', percent: '100%' },
    { month: '202303', goal: '400,000,000', grade: '300,000,000', require: '100,000,000', percent: '75%' },
    { month: '1분기', goal: '1,200,000,000', grade: '1,100,000,000', require: '100,000,000', percent: '91.67%' },

    { month: '202304', goal: '400,000,000', grade: '500,000,000', require: '0', percent: '125%' },
    { month: '202305', goal: '400,000,000', grade: '200,000,000', require: '200,000,000', percent: '50%' },
    { month: '202306', goal: '600,000,000', grade: '600,000,000', require: '0', percent: '100%' },
    { month: '2분기', goal: '1,400,000,000', grade: '1,300,000,000', require: '100,000,000', percent: '92.86%' },

    { month: '202307', goal: '800,000,000', grade: '800,000,000', require: '0', percent: '100%' },
    { month: '202308', goal: '800,000,000', grade: '900,000,000', require: '0', percent: '112.50%' },
    { month: '202309', goal: '600,000,000', grade: '600,000,000', require: '0', percent: '100%' },
    { month: '3분기', goal: '2,200,000,000', grade: '2,300,000,000', require: '0', percent: '104.55%' },

    { month: '202310', goal: '600,000,000', grade: '600,000,000', require: '0', percent: '100%' },
    { month: '202311', goal: '800,000,000', grade: '500,000,000', require: '300,000,000', percent: '62.50%' },
    { month: '202312', goal: '800,000,000', grade: '1,000,000,000', require: '0', percent: '125%' },
    { month: '4분기', goal: '2,200,000,000', grade: '2,100,000,000', require: '100,000,000', percent: '95.45%' },

    { month: '총계', goal: '7,000,000,000', grade: '6,800,000,000', require: '300,000,000', percent: '97.14%' }
]);
const searchQuery = ref('');
const searchBy = ref('Team');
const filteredteams = ref(teams.value);

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
    @import url('@/assets/css/performance/TeamPerformance.css');
</style>
