// Esperar a que se cargue la página
$(function() {
    // Al presionar un enlace
    $('a').on('click', function() {
        // Mostrar diálogo de carga
        $.LoadingOverlay('show');
    });
});