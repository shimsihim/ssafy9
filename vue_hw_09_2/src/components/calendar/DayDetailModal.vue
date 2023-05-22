<template>
  <b-modal v-model="showModal" title="이벤트 정보" class="custom-modal">

    <b-table striped hover :items="Records" :fields="fields" :labels="labels" @row-clicked="seletOne"></b-table>
  </b-modal>
</template>
  
<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      fields: [ { key: 'record_part', label: '운동부위' }, { key: 'record_ex_time', label: '운동 시간' },{ key: 'record_ex_memo', label: '메모' }], // Records에서 원하는 값 전하기 및 순서
      
      showModal: false,
      eventInfo: ''
    };
  },
  methods: {
    seletOne(item) {
      this.$emit("selectEvent",item)
    }
  },

  computed: {
    ...mapState(["Records"]),
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