import { Message } from "element-ui";
export function showMessage(close,type,msg,duration) {
    Message({
        showClose: close,
        message: msg,
        type: type,
        duration: duration
    });
}