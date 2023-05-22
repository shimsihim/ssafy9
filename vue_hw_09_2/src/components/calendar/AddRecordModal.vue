<template>
  <b-modal v-model="addModal" title="기록 추가" class="custom-modal" :footer-bg-variant="footerBgVariant"
    :footer-text-variant="footerTextVariant">
    <label for="example-datepicker">Choose a date</label>
    <b-form-datepicker id="example-datepicker" v-model="date" class="mb-2"></b-form-datepicker>
    <br>
    <b-form-select v-model="choice" :options="exOrFood"></b-form-select>
    <div v-if="choice === `운동`">
      <br>
      <p>운동부위 </p>
      <b-form-select v-model="selected" :options="options"></b-form-select>
      <br><br>

      <p>운동시간</p>
      <b-form-input v-model="timeRecord" :id="`type-number`" type="number" min="0"></b-form-input>
      <br>
      <p>메모</p>
      <b-form-textarea id="textarea" v-model="text" placeholder="메모를 적으세요" rows="3" max-rows="6"></b-form-textarea>
    </div>
    <!--  -->
    <!--  -->
    <!--  -->
    <div v-else>
      <br>
      <p>식사시간 </p>
      <b-form-select v-model="eatingTime" :options="eatTimeOption"></b-form-select>
      <br><br>

      <p>먹은 음식</p>
      <br>
      <b-form-textarea id="textarea" v-model="text" placeholder="메모를 적으세요" rows="3" max-rows="6"></b-form-textarea>
      <p>양</p>
      <b-form-input v-model="timeRecord" :id="`type-number`" type="number" min="0"></b-form-input>
    </div>
    <!-- 여기 아래부터 제출을 위한 풋터로 이벤트 발생을 위해 따로 정의함 -->
    <template #modal-footer>
      <div class="w-100">

        <b-button variant="primary" size="md" class="float-center" @click="submitRecord" style="width: 250px;">
          제출
        </b-button>
      </div>
    </template>
    <!-- 여기 까지 -->
  </b-modal>
</template>
  
<script>

export default {
  data() {
    return {
      eatingTime: "아침",
      eatTimeOption: [
        { value: '아침', text: '아침', },
        { value: '점심', text: '점심' },
        { value: '저녁', text: '저녁' },
      ],
      exOrFood: [
        { value: '운동', text: '운동', },
        { value: '음식', text: '음식' },
      ],
      choice: "운동",
      date: '',
      selected: "가슴",
      options: [
        { value: '가슴', text: '가슴', },
        { value: '등', text: '등' },
        { value: '하체', text: '하체' },
        { value: '복근', text: '복근' }
      ],
      timeRecord: 0,
      text: "",
      addModal: false,

    };
  },
  methods: {
    submitRecord() {
      console.log("여기 addrecordmodel의 제출 이벤트메소드")

      if (this.date === "" || this.timeRecord < 0 || this.text === "") {
        alert("정확하게 입력 바랍니다")
      }
      let recordForm = {
        date: this.date,
        part: this.selected,
        time: this.timeRecord,
        memo: this.text
      }
      this.$store.dispatch("submitExRecord", recordForm);
      this.date = '';
      this.selected = "가슴";
      this.timeRecord = 0;
      this.text = '';
      this.addModal = false
    }
  },


};
</script>
<style>
.custom-modal .modal-dialog {
  overflow-y: auto;
  display: flex;
  flex-direction: row;
}
</style>