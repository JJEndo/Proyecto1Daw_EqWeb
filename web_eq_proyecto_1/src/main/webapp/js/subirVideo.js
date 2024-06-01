const uploadForm = document.getElementById('upload-form');
const videoInput = document.getElementById('video-input');
const uploadBtn = document.getElementById('upload-btn');
const progressBarFill = document.getElementById('progress-bar-fill');
const uploadStatus = document.getElementById('upload-status');

uploadBtn.addEventListener('click', () => {
    const file = videoInput.files[0];
    if (file) {
        const formData = new FormData(uploadForm);
        formData.append('video', file);

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'C:\Users\jjend\git\repository\web_eq_proyecto1\src\main\webapp\videosSubidos', true);

        xhr.upload.addEventListener('progress', (event) => {
            const percent = (event.loaded / event.total) * 100;
            progressBarFill.style.width = percent + '%';
        });

        xhr.onload = () => {
            if (xhr.status === 200) {
                uploadStatus.textContent = '¡El video se ha subido correctamente!';
            } else {
                uploadStatus.textContent = 'Error al subir el video. Por favor, inténtalo de nuevo.';
            }
        };

        xhr.onerror = () => {
            uploadStatus.textContent = 'Error al subir el video. Por favor, inténtalo de nuevo.';
        };

        xhr.send(formData);
    } else {
        uploadStatus.textContent = 'Por favor, selecciona un video antes de subirlo.';
    }
});

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
            fetch(`/ListarVideos?id=${id}&op=2`)  // Utilizar op=2 para obtener los datos del video
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
                document.getElementById("titulo").value = datos.titulo || '';
                document.getElementById("director").value = datos.director || '';
                document.getElementById("musica").value = datos.musica || '';
                document.getElementById("sinopsis").value = datos.sinopsis || '';
                document.getElementById("foto").value = datos.foto || '';
            } else {
                console.error('Datos no encontrados o inválidos');
            }
        }