// var now_time = new Date().getTime();
// document.getElementById("getdate").innerText= now_time;
function getBank_account() {
    var bank_no = Math.floor(Math.random() * 16);
    var prefix = "";
    switch (bank_no) {
        case "1": prefix = "552599"; break;
        case "2": prefix = "404119"; break;
        case "3": prefix = "404121"; break;
        case "4": prefix = "519412"; break;
        case "5": prefix = "403361"; break;
        case "6": prefix = "558730"; break;
        case "7": prefix = "520083"; break;
        case "8": prefix = "622280"; break;
        case "9": prefix = "458123"; break;
        case "10": prefix = "521899"; break;
        case "11": prefix = "622260"; break;
        case "12": prefix = "622760"; break;
        case "13": prefix = "427020"; break;
        case "14": prefix = "427030"; break;
        case "15": prefix = "622210"; break;
        case "16": prefix = "622215"; break;
        default: prefix = "955880"; break;
    }
    for (var j = 0; j < 13; j++) {
        prefix = prefix + Math.floor(Math.random() * 10);


    }
    // console.log(Math.random() * 10);
    // console.log(Math.floor(Math.random() * 10));
    document.getElementById("getcardno").innerText= prefix;
}

function getyyyyMMdd(){
    var d = new Date();
    var curr_date = d.getDate();

    var curr_month = d.getMonth() + 1;
    var curr_year = d.getFullYear();

    String(curr_month).length < 2 ? (curr_month = "0" + parseInt(Math.random()*8+1)): curr_month;
    String(curr_date).length < 2 ? (curr_date = "0" + parseInt(Math.random()*8+1)): curr_date;
    var yyyyMMdd = (curr_year - parseInt(Math.random()*30+18)) + "" + curr_month +""+ curr_date;
    return yyyyMMdd;
}


function getId_no(){
    var coefficientArray = [ "7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"];
    // 加权因子
    var lastNumberArray = [ "1","0","X","9","8","7","6","5","4","3","2"];
    // 校验码
    var address = "420101";
    // 住址
    var birthday = getyyyyMMdd();
    // 生日
    var s = Math.floor(Math.random()*10).toString() + Math.floor(Math.random()*10).toString() + Math.floor(Math.random()*10).toString();
    var array = (address + birthday + s).split("");
    var total = 0;
    for(i in array){ total = total + parseInt(array[i])*parseInt(coefficientArray[i]); }
    var lastNumber = lastNumberArray[parseInt(total%11)];
    var id_no_String = address + birthday + s + lastNumber;
    document.getElementById("getuserno").innerText= id_no_String;
}

function getall_no(){
    getBank_account();
    getId_no();
}

getBank_account();
getId_no();