$(function() {
    $('form').validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true
            }
        },
        messages: {
            email: {
                required: "Por favor, ingresa tu correo electrónico",
                email: "Por favor, ingresa un correo electrónico válido"
            },
            password: {
                required: "Por favor, ingresa tu contraseña"
            }
        },
        submitHandler: function(form) {
            $.LoadingOverlay('show');
            form.submit();
        }
    });
});