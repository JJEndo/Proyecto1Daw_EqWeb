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
    /*console.log(`Llamada a fetch con id=${id} y op=${op}`);
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
*/

	function llamada(id, op){
			fetch('FormUsuarios?id='+id+"&op="+op)
			.then(response => response.json())
			.then(data => pintarFormulario(data))
		
	}
		/**
 * Funci�n para otener el valor de un parametro en el GET 
 */
	function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}


   		function validarFormulario(){
			   
		    let nombre = document.getElementById('nombre').value;
			let email = document.getElementById('mail').value;
   		    let tel = document.getElementById('tel').value;

			let ok = true;
			if(nombre == ""){
				ok = false;
				document.getElementById('nombre').style.background = "red";
			}
			
			if(email == ""){
				ok = false;
				document.getElementById('mail').style.background = "red";
			}
			
			if(tel == ""){
				ok = false;
				document.getElementById('tel').style.background = "red";
			}
			
			if(ok == true){
				
				document.getElementById("altas").submit();
				
			}
				   
		}
		
		
		function pintarFormulario(datos){
			document.getElementById("id").value = datos.id;
			document.getElementById("nombre").value = datos.nombre;
			document.getElementById("mail").value = datos.mail;
			document.getElementById("tel").value = datos.tel;
			document.getElementById("permiso").value = datos.permiso;


		}
		
			
		window.onload = function(){
			let id = getParameterByName("id");
			let op = getParameterByName("op");
			llamada(id,op);
			
		}
		