window.onload = function() {
    console.log('Window loaded');
    llamada();
};

document.addEventListener("DOMContentLoaded", function() {
    console.log('DOM fully loaded and parsed');
    llamada();
});

function llamada() {
    console.log('Iniciando fetch para ListarVideos');
    fetch('ListarVideos?op=1')
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos', data);
            if (Array.isArray(data)) {
                pintarTabla(data);
            } else {
                console.error('Datos recibidos no son un array:', data);
            }
        })
        .catch(error => console.error('Error fetching data:', error));
}

function pintarTabla(datos) {
    const listadoElement = document.getElementById("listado");
    if (!listadoElement) {
        console.error('Elemento con id "listado" no encontrado');
        return;
    }

    console.log('Pintando la tabla con datos', datos);
    const filasHtml = datos.map(({ id, titulo, director, musica, sinopsis, foto }) => `
        <tr>
            <td>${id}</td>
            <td>${titulo}</td>
            <td>${director}</td>
            <td>${musica}</td>
            <td>${sinopsis}</td>
            <td>
                ${foto ? `<video width="320" height="240" controls>
                    <source src="${getContextPath()}/videosSubidos/${foto}" type="video/mp4">
                    Your browser does not support the video tag.
                </video>` : 'No video available'}
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Director</th>
            <th>Música</th>
            <th>Sinopsis</th>
            <th>Video</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    listadoElement.innerHTML = tablaHtml;
}

