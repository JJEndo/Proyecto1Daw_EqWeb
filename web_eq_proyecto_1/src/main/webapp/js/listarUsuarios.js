
/*function llamada(){
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


function llamada(){
    fetch('FormUsuarios?op=1')
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => pintarTabla(data))
        .catch(error => console.error('Error fetching data:', error));
}


function pintarTabla(datos){
	    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
			<td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'>${filasHtml}</table>`;
    document.getElementById("listado").innerHTML = tablaHtml;
}

window.onload = function(){
	
	llamada();
	
}

function llamada() {
    fetch('FormUsuarios?op=1')
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => pintarTabla(data))
        .catch(error => console.error('Error fetching data:', error));
}

function pintarTabla(datos) {
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Permiso</th>
            <th>Acciones</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    document.getElementById("listado").innerHTML = tablaHtml;
}

window.onload = function() {
    console.log('Window loaded');
    llamada();
}



function llamada() {
    console.log('Iniciando fetch para FormUsuarios');
    fetch('FormUsuarios?op=1')
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos', data);
            pintarTabla(data);
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
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Permiso</th>
            <th>Acciones</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    listadoElement.innerHTML = tablaHtml;
}
*
		
		window.onload = function() {
    console.log('Window loaded');
    llamada();
}

document.addEventListener("DOMContentLoaded", function() {
    console.log('DOM fully loaded and parsed');
    llamada();
});

function llamada() {
    console.log('Iniciando fetch para FormUsuarios');
    fetch('FormUsuarios?op=1')
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos', data);
            pintarTabla(data);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function borrar(id){
	if(confirm("Seguro que quieres borrar")){
    fetch(`FormUsuarios?id=${id}&op=3`)
        .then(response => response.json())
        .then(data => pintarTabla(data))
        .catch(error => console.error('Error:', error))
        }else{
			
		}
}


function pintarTabla(datos) {
    const listadoElement = document.getElementById("listado");
    if (!listadoElement) {
        console.error('Elemento con id "listado" no encontrado');
        return;
    }

    console.log('Pintando la tabla con datos', datos);
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
            <td><a href="javascript:borrar(${id})">Borrar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Permiso</th>
            <th>Acciones</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    listadoElement.innerHTML = tablaHtml;
}


// Función para realizar una solicitud GET a tu servlet y actualizar la tabla
function actualizarTabla() {
    fetch('FormUsuarios?op=1')
        .then(response => response.json())
        .then(datos => {
            if (Array.isArray(datos)) {
                pintarTabla(datos);
            } else {
                console.error("Datos no son un array:", datos);
            }
        })
        .catch(error => console.error('Error al actualizar la tabla:', error));
}

// Función para eliminar una persona y actualizar la tabla
function eliminarPersona(id) {
    fetch(`FormUsuarios?op=3&id=${id}`, {
        method: 'GET'
    })
    .then(response => response.json())
    .then(datos => {
        if (Array.isArray(datos)) {
            pintarTabla(datos);
        } else {
            console.error("Datos no son un array:", datos);
        }
    })
    .catch(error => console.error('Error al eliminar persona:', error));
}

// Función para pintar la tabla
function pintarTabla(datos) {
    const tabla = document.getElementById('tablaUsuarios');
    tabla.innerHTML = '';

    datos.forEach(persona => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td>${persona.id}</td>
            <td>${persona.nombre}</td>
            <td>${persona.apellido}</td>
            <td>
                <button onclick="eliminarPersona(${persona.id})">Eliminar</button>
            </td>
        `;
        tabla.appendChild(fila);
    });
}

// Llamar a actualizarTabla() para cargar la tabla inicialmente
document.addEventListener('DOMContentLoaded', () => {
    actualizarTabla();
});
*
window.onload = function() {
    console.log('Window loaded');
    llamada();
}

document.addEventListener("DOMContentLoaded", function() {
    console.log('DOM fully loaded and parsed');
    llamada();
});

function llamada() {
    console.log('Iniciando fetch para FormUsuarios');
    fetch('FormUsuarios?op=1')
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
        fetch(`FormUsuarios?id=${id}&op=3`)
            .then(response => response.json())
            .then(data => {
                console.log('Datos después de borrar:', data);
                if (Array.isArray(data)) {
                    pintarTabla(data);
                } else {
                    console.error('Datos recibidos no son un array:', data);
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
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
            <td><a href="javascript:borrar(${id})">Borrar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Permiso</th>
            <th>Acciones</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    listadoElement.innerHTML = tablaHtml;
}
*

window.onload = function() {
    console.log('Window loaded');
    llamada();
}

document.addEventListener("DOMContentLoaded", function() {
    console.log('DOM fully loaded and parsed');
    llamada();
});

function llamada() {
    console.log('Iniciando fetch para FormUsuarios');
    fetch('FormUsuarios?op=1')
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
        fetch(`FormUsuarios?id=${id}&op=3`)
            .then(response => response.json())
            .then(data => {
                console.log('Datos después de borrar:', data);
                if (Array.isArray(data)) {
                    pintarTabla(data);
                } else {
                    console.error('Datos recibidos no son un array:', data);
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
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
            <td><a href="javascript:borrar(${id})">Borrar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Permiso</th>
            <th>Acciones</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    listadoElement.innerHTML = tablaHtml;
}
*/
window.onload = function() {
    console.log('Window loaded');
    llamada();
}

document.addEventListener("DOMContentLoaded", function() {
    console.log('DOM fully loaded and parsed');
    llamada();
});

function llamada() {
    console.log('Iniciando fetch para FormUsuarios');
    fetch('FormUsuarios?op=1')
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
        fetch(`FormUsuarios?id=${id}&op=3`)
            .then(response => {
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
    const filasHtml = datos.map(({ id, nombre, email, permiso }) => `
        <tr>
            <td>${id}</td>
            <td>${nombre}</td>
            <td>${email}</td>
            <td>${permiso}</td>
            <td><a href="altaUsuario.html?id=${id}&op=2" class="btn-editar">Editar</a></td>
            <td><a href="javascript:borrar(${id})">Borrar</a></td>
        </tr>
    `).join('');

    const tablaHtml = `<table class='listaTabla'><thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Permiso</th>
            <th>Acciones</th>
        </tr>
    </thead><tbody>${filasHtml}</tbody></table>`;
    listadoElement.innerHTML = tablaHtml;
}

function busquedaPorTipo(tipo) {
    // Convertir 'tipo' a número y validar
    tipo = Number(tipo);

    if (isNaN(tipo)) {
        console.error('El tipo debe ser un número válido');
        return;
    }

    console.log('Iniciando fetch para FormUsuarios');
    fetch(`FormUsuarios?op=4&tipoUsuario=${encodeURIComponent(tipo)}`)
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
