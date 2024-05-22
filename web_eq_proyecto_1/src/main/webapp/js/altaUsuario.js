/**
 * 
 
function llamada(id, op) {
    fetch(`FormUsuarios?id=${id}&op=${op}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => pintarFormulario(data))
        .catch(error => console.error('Error fetching data:', error));
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    const regex = new RegExp(`[\\?&]${name}=([^&#]*)`);
    const results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function pintarFormulario(datos) {
    document.getElementById("id").value = datos.id;
    document.getElementById("nombre").value = datos.nombre;
    document.getElementById("email").value = datos.mail;
    document.getElementById("permiso").value = datos.permiso;
}

window.onload = function() {
    const id = getParameterByName("id");
    const op = getParameterByName("op");
    llamada(id, op);
}

function llamada(id, op) {
    console.log(`Llamada a fetch con id=${id} y op=${op}`);
    fetch(`FormUsuarios?id=${id}&op=${op}`)
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos del servidor', data);
            pintarFormulario(data);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    const regex = new RegExp(`[\\?&]${name}=([^&#]*)`);
    const results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function pintarFormulario(datos){
	document.getElementById("id").value = datos.id;
	document.getElementById("nombre").value = datos.nombre;
	document.getElementById("email").value = datos.email;
	document.getElementById("permiso").value = datos.permiso;
	
}

window.onload = function() {
    let id = getParameterByName("id");
    let op = getParameterByName("op");
    console.log(`Parámetros obtenidos de la URL: id=${id}, op=${op}`);
    llamada(id, op);
}

/*
window.onload = function() {
    const id = getParameterByName("id");
    const op = getParameterByName("op");
    console.log(`Parámetros obtenidos de la URL: id=${id}, op=${op}`);
    if (id && op) {
        llamada(id, op);
    } else {
        console.log('ID y/o OP no encontrados en la URL');
    }
};

function llamada(id, op) {
    console.log(`Llamada a fetch con id=${id} y op=${op}`);
    fetch(`FormUsuarios?id=${id}&op=${op}`)
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos del servidor', data);
            pintarFormulario(data);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    const regex = new RegExp(`[\\?&]${name}=([^&#]*)`);
    const results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function pintarFormulario(datos) {
    document.getElementById("id").value = datos.id || '';
    document.getElementById("nombre").value = datos.nombre || '';
    document.getElementById("email").value = datos.email || '';
    document.getElementById("permiso").value = datos.permiso || '';
}
*
window.onload = function() {
    const id = getParameterByName("id");
    const op = getParameterByName("op");
    console.log(`Parámetros obtenidos de la URL: id=${id}, op=${op}`);
    if (id && op) {
        llamada(id, op);
    } else {
        console.log('ID y/o OP no encontrados en la URL');
    }
};

function llamada(id, op) {
    console.log(`Llamada a fetch con id=${id} y op=${op}`);
    fetch(`FormUsuarios?id=${id}&op=${op}`)
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos del servidor', data);
            pintarFormulario(data);
        })
        .catch(error => console.error('Error fetching data', error));
}

function getParameterByName(name) {
    name = name.replace(/[\[\]]/g, "\\$&");
    const regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
          results = regex.exec(location.search);
    return results ? decodeURIComponent(results[2].replace(/\+/g, " ")) : null;
}

function pintarFormulario(datos) {
    if (datos) {
        document.getElementById("id").value = datos.id || '';
        document.getElementById("nombre").value = datos.nombre || '';
        document.getElementById("email").value = datos.email || '';
        document.getElementById("permiso").value = datos.permiso || '';
    } else {
        console.error('Datos no encontrados o inválidos');
    }
}
*/
window.onload = function() {
    const id = getParameterByName("id");
    const op = getParameterByName("op");
    console.log(`Parámetros obtenidos de la URL: id=${id}, op=${op}`);
    if (id && op) {
        llamada(id, op);
    } else {
        console.log('ID y/o OP no encontrados en la URL');
    }
};

function llamada(id, op) {
    console.log(`Llamada a fetch con id=${id} y op=${op}`);
    fetch(`/FormUsuarios?id=${id}&op=${op}`)
        .then(response => {
            console.log('Respuesta recibida', response);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos recibidos del servidor', data);
            pintarFormulario(data);
        })
        .catch(error => console.error('Error fetching data', error));
}

function getParameterByName(name) {
    name = name.replace(/[\[\]]/g, "\\$&");
    const regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
          results = regex.exec(location.search);
    return results ? decodeURIComponent(results[2].replace(/\+/g, " ")) : null;
}

function pintarFormulario(datos) {
    if (datos) {
        document.getElementById("id").value = datos.id || '';
        document.getElementById("nombre").value = datos.nombre || '';
        document.getElementById("email").value = datos.email || '';
        document.getElementById("permiso").value = datos.permiso || '';
    } else {
        console.error('Datos no encontrados o inválidos');
    }
}
