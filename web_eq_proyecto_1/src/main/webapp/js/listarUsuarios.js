function llamada(){
    fetch('FormUsuarios')
    .then(response => {
        if (!response.ok) {
            // Si la respuesta del servidor no es exitosa, lanzamos un error.
            throw new Error('Network response was not ok: ' + response.statusText);
        }
        return response.text(); // Obtenemos la respuesta como texto para poder verificarla antes de parsearla.
    })
    .then(text => {
        console.log("Response Text:", text); // Imprimimos el texto de la respuesta para ver qué está devolviendo el servidor.
        try {
            const data = JSON.parse(text); // Intentamos convertir el texto a JSON.
            pintarTabla(data); // Si el parseo es exitoso, procesamos los datos.
        } catch (error) {
            // Si hay un error al parsear el JSON, lo capturamos y mostramos.
            throw new Error('Error parsing JSON: ' + error.message);
        }
    })
    .catch(error => {
        // Capturamos cualquier error que ocurra durante la operación de fetch o el procesamiento del JSON.
        console.error('Error fetching data:', error);
    });
}

function pintarTabla(datos) {
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td>${permiso}</td>
            <td><a href="FormUsuarios?id=${id}" class="btn-editar">Editar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'>${filasHtml}</table>`;
    document.getElementById("listado").innerHTML = tablaHtml;
}

document.addEventListener('DOMContentLoaded', function() {
    llamada(); // Llamamos a la función al cargar la página.
});

