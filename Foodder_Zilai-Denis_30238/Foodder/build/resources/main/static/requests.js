
document.getElementById('button').addEventListener('click',function(){login()});
console.log(document.getElementById('button'));
function login(){
        var name = document.getElementById("name-c4d8").value;
        var password = document.getElementById("email-c4d8").value;

        console.log(name.toString()+" "+password.toString());
        const params = {
                name: name,
                password: password
        }
        var paramJSON = JSON.stringify(params);
        console.log(paramJSON);
        var xhr = new XMLHttpRequest();
        xhr.open('PUT','/users',true);
        xhr.setRequestHeader("Content-Type","application/json;charset=UTF-8");
        xhr.onload=function(){
                if(this.status==200){
                        var user = JSON.parse(this.responseText);
                        console.log(user);

                }
        }
        xhr.send(paramJSON);
}