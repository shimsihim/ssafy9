


<template>
    <div>
        <h1>Demo App</h1>
        <FullCalendar :options='calendarOptions' />
        <DayDetailModal ref="eventModal" @selectEvent="convertModal"></DayDetailModal>
        <button @click="addRecord">이벤트 보기</button>
        <AddRecordModal ref="add"></AddRecordModal>
        <UpdateRecordModal ref="update"></UpdateRecordModal>
    </div>
</template>



<script>

import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import { mapState } from "vuex";
import DayDetailModal from './DayDetailModal.vue';// 모달창 추가
import AddRecordModal from './AddRecordModal.vue';// 기록 모달창 추가
import UpdateRecordModal from './UpdateRecordModal.vue';// 수정 모달창 추가
export default {

    components: {
        FullCalendar, DayDetailModal, AddRecordModal, UpdateRecordModal
    },
    data: function () {
        return {
            showModal: false, //true일 때 하루 기록 모달창 보여짐
            addModal: false, //기록추가 모달 제어
            updateModal: false, //기록 수정용 

            //여기부터 캘린더 옵션
            calendarOptions: {
                plugins: [dayGridPlugin, interactionPlugin], // 월뷰, 상호작용 플러그인
                initialView: 'dayGridMonth', // 초기 월 뷰로 보여줌
                weekends: true, // 주말 나타냄
                dateClick: this.handleDateClick, // 날짜 클릭 시 실행될 함수
                events: [
                    { title: "안녕", start: '2023-05-17' }
                ],
                headerToolbar: { // 달력 상부에 띄울 항목들
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth'
                },
                eventDisplay: 'list-item'



            }
            //여기까지 캘린더 옵션
        }
    },
    created() {

        this.$store.dispatch("setMonthRecord");




    },
    methods: {
        handleDateClick: function (arg) { // 날짜 클릭 시 발생 메소드로 ARG에는 해당 날짜의 기록들이 들어있음

            this.$store.dispatch("getRecods", arg.dateStr); // 해당 날짜의 기록 가져옴
            // 모달 표시
            this.$refs.eventModal.showModal = true;
        },
        addRecord() {
            // 기록 추가 표시
            this.$refs.add.addModal = true;
        },
        convertModal(item) {
            this.$refs.eventModal.showModal = false
            this.$refs.update.updateModal = true
            this.$refs.update.pro = item


            var d = new Date(item.record_ex_date) // 날짜 형식으로 변환
            this.$refs.update.pro.record_ex_date = d.getFullYear() + "-" + (((d.getMonth() + 1).toString().length) == 1 ? ("0" + (d.getMonth() + 1)) : d.getMonth() + 1) + "-" + (((d.getDate()).toString().length) == 1 ? ("0" + (d.getDate())) : d.getDate())
            

        }


    },
    computed: {
        ...mapState(["Records"]),
        ...mapState(["MonthRecords"]),
        check_month_record() { return this.$store.getters.get_month_record } // 전체기록 getter로 가져오기
    },
    watch: {
        check_month_record(MonthRecords) { // 전체기록이 변경되면 이를 감시하고 computed의 getter를 통해 가져옴
            this.calendarOptions.events = [];
            for (var i = 0; i < MonthRecords.length; ++i) {
                var d = new Date(MonthRecords[i].record_ex_date) // 날짜 형식으로 변환
                this.calendarOptions.events.push({ title: MonthRecords[i].record_ex_memo, start: d.getFullYear() + "-" + (((d.getMonth() + 1).toString().length) == 1 ? ("0" + (d.getMonth() + 1)) : d.getMonth() + 1) + "-" + (((d.getDate()).toString().length) == 1 ? ("0" + (d.getDate())) : d.getDate()), borderColor: "red", }) // 배열을 초기화한 후 다시 넣어줌
                //위에서 식이 길어진 이유는 스프링에서 js로 date값을 받을 때 밀리초 단위로 바꿔 받는데
                //이를 한번에 2000-01-01과 같은 형식으로 바꾸는 법을 못찾겠음
                //단순하게 바꿔서 자르면 1일 이전의 값이 들어감....

            }
        }
    }
}
</script>
<style>
:root {
    --fc-border-color: black;
    --fc-daygrid-event-dot-width: 5px;
}

* {
    color: black;
}

.fc-daygrid-day-number {
    color: black;
}

.fc-col-header-cell-cushion {
    color: black;
    /* 월화수목금토일 색상 */
    background-color: #4AC4F3;
}

.fc-scrollgrid-sync-inner {
    background-color: #4AC4F3;
    /* 달력의 배경색, 즉 네모칸의 배경색 */

}
</style>