import { createRouter, createWebHashHistory } from "vue-router";

const app = () => import("../App.vue");
const login = () => import("../view/login.vue");
const index = () => import("../view/index.vue");
const person_admin = () => import("../components/PersonAdmin.vue");
const history_data = () => import("../components/HistoryData.vue");
const alart_record = () => import("../components/AlartRecord.vue");

const routes = [
  {
    path: "/",
    name: "app",
    component: app,
  },
  {
    path: "/login",
    name: "login",
    component: login,
  },
  {
    path: "/index",
    name: "index",
    component: index,
    children: [
      {
        path: "person",
        name: "Person",
        component: person_admin,
      },
      {
        path: "history",
        name: "History",
        component: history_data,
      },
      {
        path: "alart",
        name: "Alart",
        component: alart_record,
      },
    ]
  },
  
];

export const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
});
