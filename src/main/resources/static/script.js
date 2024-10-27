//selectors
const todoBtn = document.querySelector(".todo-button");
const todoInput = document.querySelector(".todo-input");
//(event listener
todoBtn.addEventListener("click", addTask);

//function
function addTask(e) {
    e.preventDefault();
    console.log("Hello i am input");
}
