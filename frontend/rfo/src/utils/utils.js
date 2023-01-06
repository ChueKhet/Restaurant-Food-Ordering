import constant from "./constant";
import http from "./http";
import router from '../router/index'

function goToScreen(screenPath) {
    router.push({ path: screenPath});
}

function goToScreenWithData(path, name, data){
    let temp = {
        path: path,
        name: name,
        params: data
    };

    router.push(temp);
}

export default { constant, http, goToScreen, goToScreenWithData };
