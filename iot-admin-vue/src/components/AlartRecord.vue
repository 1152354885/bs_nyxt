<template>
  <div class="flex justify-center">
    <div class="overflow-x-auto">
        开始时间:<input type="date" v-model="date1"/>  结束时间:<input type="date" v-model="date2" />&nbsp
       <button v-on:click="search(date1,date2)" class="btn btn-outline">Search</button>
       
      <table class="table w-full">
        <!-- head -->
        <thead>
          <tr>
            <th>报警条数</th>
            
            <th>co2浓度</th>
            
            <th>报警时间</th>
          </tr>
        </thead>
        <tbody>
          <!-- row 1 -->
          <tr v-for="item in tableData">
            <th>{{ item.id }}</th>
        
            <td>{{ item.co2 }}</td>
           
            <td>{{ item.time }}</td>
            
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: [],
    };
  },

  mounted() {
    this.getPlanList();
  },

  beforeRouterEnter(to, from, next) {
    this.getPlanList();
  },

  methods: {
    getPlanList() {
      this.axios
        .get("/api/iot/warn")
        .then((res) => {
          console.log(res)
          if (res.status === 200) {
            this.tableData = res.data.data.list;
            console.log(this.tableData);
          }
        })
        .catch((err) => {
          console.log(err);
          alert("")
        });
    },

    search(date1,date2){
      const data = {
                
                'date1': date1,
                'date2': date2,
                
                
            }
            console.log(data)
           this.axios
          .post(`/api/iot/search`,data)
          .then((res) => {
              console.log(res)
              if (res.data.code === 0) {
                this.tableData = res.data.data.list;//毕设功能上最后一行代码
              }
            })
            .catch((err) => {
              console.log(err);
            });

   
  },
  },

};
</script>

<style scoped>
.has-mask {
  position: absolute;
  clip: rect(10px, 150px, 130px, 10px);
}
</style>
