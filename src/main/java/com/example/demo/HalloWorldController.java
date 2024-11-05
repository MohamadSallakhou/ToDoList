package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HalloWorldControlla {

    @GetMapping("/")
    public String sayHello() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\" />\n" +
                "    <link\n" +
                "            rel=\"stylesheet\"\n" +
                "            href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\"\n" +
                "            integrity=\"sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==\"\n" +
                "            crossorigin=\"anonymous\"\n" +
                "            referrerpolicy=\"no-referrer\"\n" +
                "    />\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />\n" +
                "    <link\n" +
                "            href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\"\n" +
                "            rel=\"stylesheet\"\n" +
                "    />\n" +
                "    <title>Todo</title>\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/vue@2\"></script>\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/Vue.Draggable/2.24.3/vuedraggable.umd.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"app\">\n" +
                "    <header>ToDoList</header>\n" +
                "\n" +
                "    <!-- NavBar Komponente -->\n" +
                "    <nav class=\"nav-bar\">\n" +
                "        <ul>\n" +
                "            <li><a href=\"#\" @click.prevent=\"currentView = 'home'\"><i class=\"fas fa-home\"></i> Home</a></li>\n" +
                "            <li><a href=\"#\" @click.prevent=\"currentView = 'tasks'\"><i class=\"fas fa-tasks\"></i> Tasks</a></li>\n" +
                "            <li><a href=\"#\" @click.prevent=\"currentView = 'about'\"><i class=\"fas fa-info-circle\"></i> About</a></li>\n" +
                "        </ul>\n" +
                "    </nav>\n" +
                "\n" +
                "    <!-- InfoBox Komponente -->\n" +
                "    <div class=\"info-box\" v-if=\"currentView === 'home'\">\n" +
                "        <h2>Willkommen zur ToDo App!</h2>\n" +
                "        <p>Dies ist die Startseite Ihrer Anwendung.</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- About Section -->\n" +
                "    <div class=\"info-box\" v-if=\"currentView === 'about'\">\n" +
                "        <h2>Über diese App</h2>\n" +
                "        <p>Diese App hilft Ihnen dabei, Ihre Aufgaben effizient zu verwalten. Entwickelt mit Liebe und JavaScript.</p>\n" +
                "    </div>\n" +
                "\n" +
                "    <!-- Todo Form und Liste -->\n" +
                "    <form v-if=\"currentView === 'tasks'\" action=\"#\" th:action=\"@{/addTask}\" method=\"post\" @submit.prevent=\"addTodo\">\n" +
                "        <input type=\"text\" name=\"description\" class=\"todo-input\" v-model=\"newTodo\" placeholder=\"Neue Aufgabe\" />\n" +
                "        <button type=\"submit\" class=\"todo-button\" :disabled=\"newTodo.trim() === ''\">\n" +
                "            <i class=\"fas fa-plus-square\"></i>\n" +
                "        </button>\n" +
                "    </form>\n" +
                "    <div class=\"todo-container\" v-if=\"currentView === 'tasks'\">\n" +
                "        <draggable v-model=\"todos\" class=\"todo-list\">\n" +
                "            <li v-for=\"(todo, index) in filteredTodos\" :key=\"index\" :class=\"{ completed: todo.completed }\" class=\"todo\">\n" +
                "                <div class=\"todo-item\" th:text=\"${todo.description}\">{{ todo.text }}</div>\n" +
                "                <span th:text=\"${todo.completed ? 'Erledigt' : 'Offen'}\"></span>\n" +
                "                <button @click=\"toggleComplete(index)\" class=\"complete-btn\">\n" +
                "                    <i class=\"fas fa-check\"></i>\n" +
                "                </button>\n" +
                "                <button @click=\"deleteTodo(index)\" class=\"trash-btn\">\n" +
                "                    <i class=\"fas fa-trash\"></i>\n" +
                "                </button>\n" +
                "            </li>\n" +
                "        </draggable>\n" +
                "    </div>\n" +
                "    <div v-if=\"currentView === 'tasks'\">\n" +
                "        <button @click=\"filter = 'all'\">Alle</button>\n" +
                "        <button @click=\"filter = 'completed'\">Erledigt</button>\n" +
                "        <button @click=\"filter = 'active'\">Nicht erledigt</button>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "    new Vue({\n" +
                "        el: '#app',\n" +
                "        data: {\n" +
                "            currentView: 'home',\n" +
                "            newTodo: '',\n" +
                "            todos: JSON.parse(localStorage.getItem('todos')) || [],\n" +
                "            filter: 'all'\n" +
                "        },\n" +
                "        computed: {\n" +
                "            filteredTodos() {\n" +
                "                if (this.filter === 'completed') {\n" +
                "                    return this.todos.filter(todo => todo.completed);\n" +
                "                } else if (this.filter === 'active') {\n" +
                "                    return this.todos.filter(todo => !todo.completed);\n" +
                "                } else {\n" +
                "                    return this.todos;\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        methods: {\n" +
                "            addTodo() {\n" +
                "                if (this.newTodo.trim() === '') {\n" +
                "                    alert('Bitte geben Sie eine Aufgabe ein.');\n" +
                "                    return;\n" +
                "                }\n" +
                "                this.todos.push({ text: this.newTodo, completed: false });\n" +
                "                this.newTodo = '';\n" +
                "                this.saveTodos();\n" +
                "            },\n" +
                "            deleteTodo(index) {\n" +
                "                if (confirm('Sind Sie sicher, dass Sie diese Aufgabe löschen möchten?')) {\n" +
                "                    this.todos.splice(index, 1);\n" +
                "                    this.saveTodos();\n" +
                "                }\n" +
                "            },\n" +
                "            toggleComplete(index) {\n" +
                "                this.todos[index].completed = !this.todos[index].completed;\n" +
                "                this.saveTodos();\n" +
                "            },\n" +
                "            saveTodos() {\n" +
                "                localStorage.setItem('todos', JSON.stringify(this.todos));\n" +
                "            }\n" +
                "        }\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
    }
}