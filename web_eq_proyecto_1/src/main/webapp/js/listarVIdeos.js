// Definición de getContextPath
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

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
    fetch(getContextPath() + '/ListarVideos?op=1')
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

function borrar(id) {
    if (confirm("¿Seguro que quieres borrar?")) {
        fetch(`/ListarVideos?id=${id}&op=3`)
            .then(response => {
                console.log('Respuesta recibida', response);
                if (!response.ok) {
                    throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                console.log('Datos después de borrar:', data);
                if (data.success) {
                    llamada(); // Vuelve a llamar para obtener la lista actualizada
                } else {
                    console.error('Error en la respuesta de borrado:', data);
                }
            })
            .catch(error => console.error('Error:', error));
    }
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
            </td>
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

