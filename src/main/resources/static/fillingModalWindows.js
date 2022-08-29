$(document).ready(function() {

    $('#modal-container-663453').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget); // Button that triggered the modal
        const recipient = button.data('whatever'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        const modal = $(this);
        //modal.find('form').attr('action', url);
        const myArray = recipient.split(",");
        modal.find('#id_view_e').attr('value',myArray[0]);
        modal.find('#name_e').attr('value',myArray[1]);
        modal.find('#lastname_e').attr('value',myArray[2]);
        modal.find('#age_e').attr('value',myArray[3]);
        modal.find('#login_e').attr('value',myArray[4]);
        modal.find('#password_e').attr('value',myArray[5]);
    })
    $('#modal-container-663454').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget); // Button that triggered the modal
        const recipient = button.data('whatever'); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        const modal = $(this);
        const myArray = recipient.split(",");
        modal.find('#id_d').attr('value',myArray[0]);
        modal.find('#name_d').attr('value',myArray[1]);
        modal.find('#Lastname_d').attr('value',myArray[2]);
        modal.find('#age_d').attr('value',myArray[3]);
        modal.find('#login_d').attr('value',myArray[4]);
        modal.find('#password_d').attr('value',myArray[5]);

        // $.each(myArray, function (index, value) {
        //
        //     if(index > 5) {
        //          $('#select_d').append($('<option>',
        //              {text: value}))
        //    }
        // });


        $( "#delete" ).click(function() {
            //window.location.href = '/admin/delete/' + myArray[0];
            deleteUser(myArray[0])
        });

    })
});

