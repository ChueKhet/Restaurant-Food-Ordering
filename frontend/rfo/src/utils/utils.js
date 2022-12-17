import constant from "./constant";
import http from "./http";
import router from '../router/index'

function goToScreen(screenPath) {
    router.push({ path: screenPath});
}

export default { constant, http, goToScreen };
