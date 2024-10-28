// JavaScript for adding functionality to the Todo List

document.addEventListener('DOMContentLoaded', function () {
    // Selectors
    const todoInput = document.querySelector('.todo-input');
    const todoButton = document.querySelector('.todo-button');
    const todoList = document.querySelector('.todo-list');
  
    // Event Listeners
    todoButton.addEventListener('click', addTodo);
    todoList.addEventListener('click', deleteCheck);
  
    // Functions
    function addTodo(event) {
      // Prevent form from submitting
      event.preventDefault();
  
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
    }
  
    function deleteCheck(event) {
      const item = event.target;
  
      // Delete Todo
      if (item.classList[0] === 'trash-btn') {
        const todo = item.parentElement;
        // Animation
        todo.classList.add('fall');
        todo.addEventListener('transitionend', function () {
          todo.remove();
        });
      }
  
      // Mark as completed
      if (item.classList[0] === 'complete-btn') {
        const todo = item.parentElement;
        todo.classList.toggle('completed');
      }
    }
  });
