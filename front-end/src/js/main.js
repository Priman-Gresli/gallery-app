const uploadEBtn = $("#btn");
const uploadWindow = $("#outer-drag");
const uploadArea = $("#drag-drop");
const downloadElm = $(".material-symbols-outlined");
const imageDivElm = $("#main-scene");
const REST_API_URL="http://localhost:8080/app";


uploadEBtn.on('click',(event)=>{
    uploadWindow.removeClass("d-none");

})
uploadWindow.on('click',(event)=>{
    if (event.target === uploadWindow[0]) {
       uploadWindow.addClass("d-none")
    }
})
$(document).on('keydown',(event=>{
    if (event.key === "Escape") {
        uploadWindow.addClass("d-none")
    }
}))
downloadElm.on('click',(event)=>{

})
loadAll();

function loadAll(){
    const ajax = $.ajax(REST_API_URL+"/images");
    ajax.done((imageList)=>{
        imageList.forEach(imageUrl=>{
            console.log(imageUrl);
            const divElm = $(`<div class="image"><span class="material-symbols-outlined">download</span></div>`);
            divElm.css('background-image', `url(${imageUrl})`);
            imageDivElm.append(divElm);
        })
    })
}


