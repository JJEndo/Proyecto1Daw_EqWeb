document.addEventListener('DOMContentLoaded', function() {
    var video = document.getElementById('videoPlayer');
    var botonPlayPause = document.getElementById('botonPlayPause');
    var botonMute = document.getElementById('botonMute');

    // Función para reproducir o pausar el video
    function togglePlayPause() {
        if (video.paused || video.ended) {
            video.play();
            botonPlayPause.textContent = 'Pause';
        } else {
            video.pause();
            botonPlayPause.textContent = 'Play';
        }
    }

    // Función para silenciar o desmutear el video
    function toggleMute() {
        if (video.muted) {
            video.muted = false;
            botonMute.textContent = 'Mute';
        } else {
            video.muted = true;
            botonMute.textContent = 'Unmute';
        }
    }

    // Event listeners para los botones
    botonPlayPause.addEventListener('click', togglePlayPause);
    botonMute.addEventListener('click', toggleMute);
});

// var video = document.getElementById("videoPlayer");
// function playPause() { 
//     if (video.paused) 
//         video.play(); 
//     else 
//         video.pause(); 
// }
// function reload() { 
//    video.load(); 
// }
// function makeLarge() { 
//     video.width = 1000; 
// }
// function makeSmall() { 
//     video.width = 250; 
// } 
// function makeNormal() { 
//     video.width = 500; 
// } 
