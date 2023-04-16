<template>
  <div class="flex justify-center">
    <div class="overflow-x-auto">
    <!-- 搜索框     -->
<div class="form-control">
  <div class="input-group">
    <input  type="text" v-model="s" class="input input-bordered" />
    <button  class="btn btn-square" v-on:click="search(s)" >
      <svg  xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
    </button>
  </div>
</div>  
&nbsp<br>


      <table class="table w-full">
        <!-- head -->
        <thead>
          <tr>
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
            <th>部门名称</th>
            <th>生日</th>
            <th>性别</th>
            <th>密保答案</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <!-- row 1 -->
          <tr v-for="item, index in tableData" :key="index">
            <th>{{ item.id }}</th>
            <td>{{ item.username }}</td>
            <td>{{ item.password }}</td>
            <td>{{ item.nickName }}</td>
            <td>{{ item.dateOfBirth }}</td>
            
            <td v-if="item.sex===1">男</td>
            <td v-else>女</td>
            <td>{{ item.pwdProtectionVal }}</td>
            <td>
              <button class="btn btn-outline btn-info" v-on:click="deleteItem(item.id)">删除</button>&nbsp
              <!-- The button to open modal -->
                      <label :for="`my-modal-${index}`" class="btn btn-outline btn-success">编辑</label>
                      <!-- Put this part before </body> tag -->
                      <input type="checkbox" :id="`my-modal-${index}`" class="modal-toggle" />
                      <div class="modal">
                        <div class="modal-box relative">
                          <label :for="`my-modal-${index}`" class="btn btn-sm btn-circle absolute right-2 top-2">✕</label>

                          <div class="form-control">
                              <label class="label">
                                <span class="label-text">用户名</span>
                              </label>
                              <label class="input-group">
                                <span>USN</span>
                                <input type="text" v-model="tableData[index].username" class="input input-bordered" />
                              </label>
                            </div>


                            <div class="form-control">
                                <label class="label">
                                  <span class="label-text">密码</span>
                                </label>
                                <label class="input-group">
                                  <span>PWD</span>
                                  <input type="text" v-model="tableData[index].password" class="input input-bordered" />
                                </label>
                             </div>

                             <div class="form-control">
                                <label class="label">
                                  <span class="label-text">部门</span>
                                </label>
                                <label class="input-group">
                                  <span>DPT</span>
                                  <input type="text" v-model="tableData[index].nickName" class="input input-bordered" />
                                </label>
                             </div>

                             <div class="form-control">
                                <label class="label">
                                  <span class="label-text">生日</span>
                                </label>
                                <label class="input-group">
                                  <span>BTD</span>
                                  <input type="text" v-model="tableData[index].dateOfBirth" class="input input-bordered" />
                                </label>
                             </div>
                              &nbsp<br>
                              &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                              &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                              &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                              &nbsp&nbsp

                              <button v-on:click="updateItem(item.id,item.username,item.password,item.nickName,item.dateOfBirth)"  class="btn btn-outline btn-warning">确认</button>
                      </div>
                  </div>
            </td>
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
        .get("/api/user/queryAll")
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

    deleteItem(itemId) {
      this.axios
        .delete(`/api/user/delete/${itemId}`)
        .then((res) => {
          console.log(res)
          if (res.status === 200) {
            this.tableData = this.tableData.filter(item => item.id != itemId)
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateItem(d,c,f,g,h){
      const data = {
                
                'id': d,
                'username': c,
                'password': f,
                'nickName': g,
                'dateOfBirth': h
                
            }
            console.log(data)
      this.axios
          .put(`/api/user/updateById`,data)
          .then((res) => {
              console.log(res)
              if (res.data.code === 0) {
                alert("修改成功")
              }
            })
            .catch((err) => {
              console.log(err);
            });

    },
    search(s){
      const data = {
                
                'keyword': s,
                
                
            }
            console.log(data)
      this.axios
          .post(`/api/user/search`,data)
          .then((res) => {
              console.log(res)
              if (res.data.code === 0) {
                this.tableData = res.data.data.list;
              }
            })
            .catch((err) => {
              console.log(err);
            });

    }
  },

};
</script>
<style></style>
