/**
 * Created by ivangeel on 26.04.17.
 */
function change() {
    document.getElementById("awaiting").style.display="none";
}

function show(message) {
    
    document.getElementById("cityname").innerHTML=message.name
    document.getElementById("description").innerHTML=message.weather.description
    document.getElementById("temp").innerHTML=message.main.temp+"°C"
    document.getElementById("pressure").innerHTML=message.main.pressure*0.75+"мм. рт. ст."
    document.getElementById("humidity").innerHTML=message.main.humidity+"%"
}

function checkWeather() {
    $.ajax({
        type: 'POST',
        data: 'city='+document.getElementById("city").value,
        url: '/ajax',
        async: true,
        success: function (mes) {
            document.getElementById("awaiting").style.display="none";
            if(mes.weather.description!=undefined) {
                document.getElementById("tabl").style.display = "block";
                show(mes)
            }
            else {
                document.getElementById("awaiting").style.display="none";
                document.getElementById("weatherpoint").innerHTML="<h2> <center>Такого города не существует</center></h2>"
            }
        },
        error: function (xhr, status, error) {
            alert(error);
            alert(xhr);
            alert(status);
        },
        xhr: function () {
            var xhr = $.ajaxSettings.xhr();
            xhr.upload.onprogress = function (evt) {
            };

            xhr.upload.onload = function () {
                document.getElementById("weatherpoint").style.display="block"
                document.getElementById("awaiting").style.display="block";
            }
            return xhr;
        }
    })
}

function checkUser() {
    alert("CHECK")
    $.ajax({
        type: 'POST',
        data: 'username='+document.getElementById("un").value+"&password="+document.getElementById("pass").value,
        url: '/signin',
        async: true,
        success: function (mes) {
            if(mes=="123"){
                document.getElementById("orow").style.display="block";
                document.getElementById("alarm").innerHTML="<center><font size=\"4\" color=\"red\"><b>Неправильный логин или пароль</b></font></center>";
            }
            else {
                location.reload()
            }
        },
        error: function (xhr, status, error) {
            alert(error);
            alert(xhr);
            alert(status);
        }
    })
}

function addUser() {
    dat='username='+document.getElementById("userreg").value+"&password="+document.getElementById("passreg").value;
    $.ajax({
        type: 'POST',
        data: dat,
        url: '/add',
        async: true,
        success: function (mes) {
            if(mes.message=="exist"){
                document.getElementById("orow2").style.display="block";
                document.getElementById("alarm2").innerHTML="<center><font size=\"4\" color=\"red\"><b>Такой пользователь уже есть</b></font></center>";
            }
            else {
                document.getElementById("un").innerHTML=document.getElementById("userreg").value;
                document.getElementById("pass").innerHTML=document.getElementById("passreg").value;
                location.reload();
            }
        }
    })
}


