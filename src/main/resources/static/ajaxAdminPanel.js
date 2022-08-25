
$(document).ready(function() {
    showAllUsers();
});

function showAllUsers() {
    $.ajax({
        url: "showUsers",
        data: {},
        type: "get",
        dataType: "json",
        success: function(data){

            $.each(data, function(index, value){
                let userDataString = [value.id, value.name,
                        value.lastName,value.age, value.login, value.password];
                let roles = ""
                $.each(value.roles, function(index_roles, value_roles){
                    roles += value_roles.name + ' ';
                  })
                $("#table-all-users > tbody").append(
                   '<tr>' +
                    '<th scope="row">' +  value.id + '</th>'+
                     '<td>'  +  value.name  + '</td>' +
                     '<td>'  +  value.lastName  + '</td>' +
                    '<td>'  +  value.age  + '</td>' +
                    '<td>'  +  value.login  + '</td>' +
                    '<td>'  +  value.password  + '</td>' +
                    '<td>'  + roles +  '</td>' +

                    '<td><a id="modal-663453" href="#modal-container-663453" role="button"' +
                    ' data-whatever = "' + userDataString + '" class="btn btn-info" data-toggle="modal">Edit</a></td>'+

                    '<td><a id="modal-663454" href="#modal-container-663454" role="button" ' +
                    'class="btn btn-danger" data-whatever="' + userDataString + '" data-toggle="modal">Delete</a></td>' +

                    '</tr>'
                );
            })
        }
    })
}

