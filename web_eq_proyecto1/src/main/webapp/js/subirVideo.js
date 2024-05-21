const uploadForm = document.getElementById('upload-form');
const videoInput = document.getElementById('video-input');
const uploadBtn = document.getElementById('upload-btn');
const progressBarFill = document.getElementById('progress-bar-fill');
const uploadStatus = document.getElementById('upload-status');

uploadBtn.addEventListener('click', () => {
    const file = videoInput.files[0];
    if (file) {
        const formData = new FormData();
        formData.append('video', file);

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'C:\Users\jjend\eclipse-workspace\web_eq_proyecto1\src\main\webapp\videosSubidos', true);

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