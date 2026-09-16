# Progress: Implement User Creation Feature (`feature/add-user`)

## 1. Project Overview & Architecture
This project is a monolithic Spring Boot web application utilizing:
- **Framework:** Spring Boot (Spring MVC, Spring Data JPA, Spring Security)
- **View Engine:** Thymeleaf with Server-Side Rendering (SSR)
- **Persistence:** PostgreSQL with Hibernate / JPA
- **Styling:** Bootstrap 5
- **Branch:** `feature/add-user` (following GitFlow workflow with Conventional Commits)

---

## 2. Milestone Breakdown & Step-by-Step Implementation

### Step 1: Branch Management & Environment Verification
- Checked out and verified the feature branch `feature/add-user` branched from `develop`.
- Verified the Maven project build with `./mvnw clean compile`.
- Inspected the existing architecture:
  - Entity: `User` (`org.exercise7.model.entity.User`)
  - Enums: `TypeUser` (`CLIENTE`, `ADMIN`)
  - Repository: `UserRepository` (`org.exercise7.model.repository.UserRepository`)
  - Service: `UserService` (`org.exercise7.model.service.UserService`)
  - Controller: `UserController` (`org.exercise7.controller.UserController`)
  - Views: `formulario.html` and `lista.html` (`src/main/resources/templates/users/`)

---

### Step 2: Service Layer (`UserService`)
- **Objective:** Implement cohesive business logic to process and persist user information while preserving existing logic completely intact.
- **Implementation:**
  - Added `saveUser(User user)` method annotated with `@Transactional`.
  - Enforces email uniqueness via `userRepository.existsByEmail(user.getEmail())`, throwing `EmailAlreadyExistsException.becauseEmailAlredyExist()`.
  - Sets default user role to `TypeUser.CLIENTE` if `user.getType()` is null, or preserves selected role.
  - Hashes user password using `passwordEncoder.encode(user.getPassword())`.
  - Persists and returns the entity via `userRepository.save(user)`.
  - Kept existing `registerUser(User user)` method intact to maintain backward compatibility.
- **Commit:** `feat(service): implement save user logic`

---

### Step 3: Controller Layer (`UserController`)
- **Objective:** Connect the HTTP endpoints to display the form and handle form submission.
- **Implementation:**
  - **GET `/usuarios/nuevo` (`showRegistrationForm`)**:
    - Initializes a new `User` command object in the model (`model.addAttribute("user", new User())`).
    - Provides `roles` list (`TypeUser.values()`) for the role dropdown selector.
    - Returns the Thymeleaf view `"users/formulario"`.
  - **POST `/usuarios/guardar` (`processRegistration`)**:
    - Validates the form data with `@Valid @ModelAttribute("user") User user, BindingResult result`.
    - If validation errors occur, repopulates `roles` in the model and returns `"users/formulario"` to display field validation messages.
    - If validation passes, delegates persistence to `userService.saveUser(user)`.
    - Redirects to `/usuarios/lista` on successful creation.
  - Updated edit endpoints (`showEditForm` and `updateUser`) to also supply `roles` for consistent model binding.
- **Commit:** `feat(controller): add endpoints for user creation`

---

### Step 4: View Layer (Thymeleaf Templates)
- **Objective:** Provide a user-friendly form interface with validation feedback and entry points into user creation.
- **Implementation:**
  - `src/main/resources/templates/users/formulario.html`:
    - Added user role dropdown (`<select th:field="*{type}">`) iterating over `roles` (`TypeUser.values()`), with inline validation error container.
    - Maintained name, lastName, email, and password fields with Bootstrap styling and Thymeleaf error bindings.
    - Updated cancel link to return smoothly to `/usuarios/lista`.
  - `src/main/resources/templates/users/lista.html`:
    - Added "+ Nuevo Usuario" button to top navbar.
    - Added "+ Agregar Usuario" button to table header card (`Resultados del Sistema`).
- **Commit:** `feat(view): create Thymeleaf form`

---

### Step 5: Verification & Compilation
- Verified Maven build with `./mvnw compile`: successful compilation with 0 errors.
- Verified all constraint annotations and Thymeleaf bindings.
- Preserved existing logic, security rules, and error handling.

---

## 3. GitFlow Conventional Commits Summary
| Commit Hash | Commit Message | Scope |
|-------------|----------------|-------|
| `832ca09`   | `feat(service): implement save user logic` | Service layer logic in `UserService` |
| `eae10f3`   | `feat(controller): add endpoints for user creation` | Controller endpoints in `UserController` |
| `e0e0de2`   | `feat(view): create Thymeleaf form` | Views `formulario.html` & `lista.html` |
| `f34aa23`   | `docs: add progress.md tracking implementation milestones` | Root documentation `progress.md` |
