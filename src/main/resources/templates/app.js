document.addEventListener('DOMContentLoaded', function () {
    // Selectors
    const todoInput = document.querySelector('.todo-input');
    const todoButton = document.querySelector('.todo-button');
    const todoList = document.querySelector('.todo-list');
    const deleteAllButton = document.querySelector('.delete-all-btn');

    // Event Listeners
    todoButton.addEventListener('click', addTodo);
    todoList.addEventListener('click', checkOrDelete);
    if (deleteAllButton) {
        deleteAllButton.addEventListener('click', deleteAllTodos);
    }

    // Functions
    function addTodo(event) {
        // Prevent form from submitting
        event.preventDefault();

        // Check if input is empty
        if (todoInput.value.trim() === "") {
            alert("Bitte geben Sie eine Aufgabe ein.");
            return;
        }

        // Create Todo div
        const todoDiv = document.createElement('div');
        todoDiv.classList.add('todo');

        // Create list item
        const newTodo = document.createElement('div');
        newTodo.innerText = todoInput.value;
        newTodo.classList.add('todo-item');
        todoDiv.appendChild(newTodo);

        // Check complete button
        const completedButton = document.createElement('button');
        completedButton.innerHTML = '<i class="fas fa-check"></i>';
        completedButton.classList.add('complete-btn');
        todoDiv.appendChild(completedButton);

        // Trash button
        const trashButton = document.createElement('button');
        trashButton.innerHTML = '<i class="fas fa-trash"></i>';
        trashButton.classList.add('trash-btn');
        todoDiv.appendChild(trashButton);

        // Append to list
        todoList.appendChild(todoDiv);

        // Clear input field
        todoInput.value = '';

        // Save todos
        saveTodos();
    }

    function saveTodos() {
        const todos = document.querySelectorAll('.todo');
        const todoArray = [];
        todos.forEach(todo => {
            const text = todo.querySelector('.todo-item').innerText;
            const completed = todo.classList.contains('completed');
            todoArray.push({ text, completed });
        });
        localStorage.setItem('todos', JSON.stringify(todoArray));
    }

    function loadTodos() {
        const todos = JSON.parse(localStorage.getItem('todos'));
        if (todos) {
            todos.forEach(todo => {
                // Create Todo div
                const todoDiv = document.createElement('div');
                todoDiv.classList.add('todo');
                if (todo.completed) {
                    todoDiv.classList.add('completed');
                }

                // Create list item
                const newTodo = document.createElement('div');
                newTodo.innerText = todo.text;
                newTodo.classList.add('todo-item');
                todoDiv.appendChild(newTodo);

                // Check complete button
                const completedButton = document.createElement('button');
                completedButton.innerHTML = '<i class="fas fa-check"></i>';
                completedButton.classList.add('complete-btn');
                todoDiv.appendChild(completedButton);

                // Trash button
                const trashButton = document.createElement('button');
                trashButton.innerHTML = '<i class="fas fa-trash"></i>';
                trashButton.classList.add('trash-btn');
                todoDiv.appendChild(trashButton);

                // Append to list
                todoList.appendChild(todoDiv);
            });
        }
    }

    function checkOrDelete(event) {
        const item = event.target;

        // Mark as completed
        if (item.classList.contains("complete-btn")) {
            const todo = item.parentElement;
            todo.classList.toggle("completed");
            saveTodos();
        }

        // Delete Todo
        if (item.classList.contains("trash-btn")) {
            const todo = item.parentElement;
            todo.classList.add("fall");
            todo.addEventListener("transitionend", () => {
                todo.remove();
                saveTodos();
            });
        }
    }

    function deleteAllTodos() {
        if (confirm('Sind Sie sicher, dass Sie alle Aufgaben löschen möchten?')) {
            while (todoList.firstChild) {
                todoList.removeChild(todoList.firstChild);
            }
            localStorage.removeItem('todos');
        }
    }

    loadTodos();
});
