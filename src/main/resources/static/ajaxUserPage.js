
$(document).ready(function() {
    showCurrentUser();

});

function showCurrentUser() {
    $.ajax({
        url: "showCurrentUser",
        data: {},
        type: "get",
        dataType: "json",
        success: function(data){
            let allRoles = ""
            data.roles.forEach( el => {
                allRoles += el.name + ' ';
            })
              $("#user_table").append(
                '<tr>' +
                '<th scope="row">' +  data.id + '</th>'+
                '<td>'  +  data.name  + '</td>' +
                '<td>'  +  data.lastName  + '</td>' +
                '<td>'  +  data.age  + '</td>' +
                '<td>'  +  data.login  + '</td>' +
                '<td>'  +  allRoles + '</td>'
            );
        }
    })
}