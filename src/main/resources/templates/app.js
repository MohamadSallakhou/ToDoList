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
    async function addTodo(event) {
        // Prevent form from submitting
        event.preventDefault();

        // Check if input is empty
        if (todoInput.value.trim() === "") {
            alert("Bitte geben Sie eine Aufgabe ein.");
            return;
        }

        // Create new task object
        const newTask = {
            description: todoInput.value,
            completed: false
        };

        // Save new task to server
        try {
            const response = await fetch('/api/tasks', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newTask)
            });

            if (!response.ok) {
                throw new Error('Fehler beim Hinzufügen der Aufgabe');
            }

            const savedTask = await response.json();
            renderTask(savedTask);
        } catch (error) {
            console.error('Fehler:', error);
        }

        // Clear input field
        todoInput.value = '';
    }

    function renderTask(task) {
        // Create Todo div
        const todoDiv = document.createElement('div');
        todoDiv.classList.add('todo');
        if (task.completed) {
            todoDiv.classList.add('completed');
        }

        // Set task ID as attribute
        todoDiv.setAttribute('data-id', task.id);

        // Create list item
        const newTodo = document.createElement('div');
        newTodo.innerText = task.description;
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
    }

    async function loadTodos() {
        try {
            const response = await fetch('/api/tasks');
            if (!response.ok) {
                throw new Error('Fehler beim Laden der Aufgaben');
            }

            const tasks = await response.json();
            tasks.forEach(task => renderTask(task));
        } catch (error) {
            console.error('Fehler:', error);
        }
    }

    async function checkOrDelete(event) {
        const item = event.target;
        const todo = item.parentElement;
        const taskId = todo.getAttribute('data-id');

        // Mark as completed
        if (item.classList.contains("complete-btn")) {
            const isCompleted = !todo.classList.contains("completed");
            try {
                const response = await fetch(`/api/tasks/${taskId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ completed: isCompleted })
                });

                if (!response.ok) {
                    throw new Error('Fehler beim Aktualisieren der Aufgabe');
                }

                todo.classList.toggle("completed");
            } catch (error) {
                console.error('Fehler:', error);
            }
        }

        // Delete Todo
        if (item.classList.contains("trash-btn")) {
            try {
                const response = await fetch(`/api/tasks/${taskId}`, {
                    method: 'DELETE'
                });

                if (!response.ok) {
                    throw new Error('Fehler beim Löschen der Aufgabe');
                }

                todo.classList.add("fall");
                todo.addEventListener("transitionend", () => {
                    todo.remove();
                });
            } catch (error) {
                console.error('Fehler:', error);
            }
        }
    }

    async function deleteAllTodos() {
        if (confirm('Sind Sie sicher, dass Sie alle Aufgaben löschen möchten?')) {
            try {
                const response = await fetch('/api/tasks', {
                    method: 'DELETE'
                });

                if (!response.ok) {
                    throw new Error('Fehler beim Löschen aller Aufgaben');
                }

                while (todoList.firstChild) {
                    todoList.removeChild(todoList.firstChild);
                }
            } catch (error) {
                console.error('Fehler:', error);
            }
        }
    }

    loadTodos();
});
