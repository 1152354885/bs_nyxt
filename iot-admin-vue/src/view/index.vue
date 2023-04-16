<template>
  <div>
    <div class="container mx-auto">
      <div class="navbar bg-base-100">
      <div class="navbar-start">
        <div class="dropdown">
          <label tabindex="0" class="btn btn-ghost lg:hidden">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M4 6h16M4 12h8m-8 6h16"
              />
            </svg>
          </label>
          <ul
            ref="dropdownMenu"
            tabindex="0"
            class="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52"
          >
            <li>
              <a @click="gotoPersonAdmin($event)">人员管理</a>
            </li>
            <li>
              <a @click="gotoHistoryData($event)">历史数据</a>
            </li>
            <li>
              <a @click="gotoAlartRecord($event)">报警记录</a>
            </li>
          </ul>
        </div>
        <router-link class="btn btn-ghost normal-case text-xl" to="/index/person"
          >April的系统管理后台
        </router-link>
      </div>
      <div class="navbar-center hidden lg:flex">
        <ul class="menu menu-horizontal p-0">
          <li>
            <a @click="gotoPersonAdmin($event)">人员管理</a>
          </li>
          <li>
            <a @click="gotoHistoryData($event)">历史数据</a>
          </li>
          <li>
            <a @click="gotoAlartRecord($event)">报警记录</a>
          </li>
        </ul>
      </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  created() {
    this.$router.push("/index/person");
  },

  methods: {
    gotoPersonAdmin(e) {
      e.preventDefault();
      this.$router.push("/index/person");
      this.$refs.dropdownMenu.blur();
    },

    gotoHistoryData(e) {
      e.preventDefault();
      this.$router.push("/index/history");
      this.$refs.dropdownMenu.blur();
    },

    gotoAlartRecord(e) {
      e.preventDefault();
      this.$router.push("/index/alart");
      this.$refs.dropdownMenu.blur();
    },
  },
};
</script>

<style></style>
