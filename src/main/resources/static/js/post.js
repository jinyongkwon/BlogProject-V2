// 1. 이벤트 리스너
$("#btn-write").click(() => {
    write();
});

$("#btn-delete").click(() => {
    deletePost()
});

// 2. 함수
let deletePost = async () => {
    let postId = $("#postId").val();
    let response = await fetch(`/s/api/post/${postId}`, {method: "DELETE"});
    let responseParse = await response.json();
    if (responseParse.code == 1) {
        alert("삭제에 성공했습니다.");
        location.href = "/";
    } else {
        alert("삭제에 실패했습니다..");
    }
}

async function write() {
    let writeDto = {
        title: $("#title").val(),
        content: $("#content").val()
    }

    //console.log(writeDto);
    let response = await fetch("/s/post", {
        method: "POST",
        body: JSON.stringify(writeDto),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
    });
    let responseParse = await response.json();

    if (responseParse.code == 1) {
        alert("글쓰기 성공");
        location.href = "/";
    } else {
        alert("글쓰기 실패");
    }
}