import CryptoJS from "crypto-js";
import { Base64 } from 'js-base64';
const key = CryptoJS.enc.Utf8.parse('1236123123113213')//替换成你的KEY 16位
const iv = CryptoJS.enc.Utf8.parse('123123132')//替换成你的IV偏移量
//加密
export function encode(str) {
    if (typeof str == 'object') { str = JSON.stringify(str) }
    var ciphertext = CryptoJS.AES.encrypt(str, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return Base64.encode(ciphertext)
}
//解密
export function decode(str) {
    var ciphertext = Base64.decode(str)
    var decrypted = CryptoJS.AES.decrypt(ciphertext, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return decrypted.toString(CryptoJS.enc.Utf8);//WordArray对象转utf8字符串
}