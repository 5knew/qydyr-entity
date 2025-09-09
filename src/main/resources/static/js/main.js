// Main JavaScript for Qydyr Entity

// User state management
class UserStateManager {
    constructor() {
        this.user = null;
        this.token = null;
        this.init();
    }

    init() {
        // Check if user is logged in on page load
        this.checkAuthStatus();
        
        // Set up event listeners
        this.setupEventListeners();
        
        // Additional check after DOM is fully loaded
        setTimeout(() => {
            this.updateUI();
        }, 100);
    }

    checkAuthStatus() {
        // Check if there's a token in localStorage
        const token = localStorage.getItem('authToken');
        if (token) {
            this.token = token;
            this.getUserInfo();
        } else {
            // If no token in localStorage, try to get it from session
            this.getTokenFromSession();
        }
    }

    async getTokenFromSession() {
        try {
            const response = await fetch('/api/v1/auth/token', {
                method: 'GET',
                credentials: 'same-origin' // Include session cookies
            });
            
            if (response.ok) {
                const data = await response.json();
                this.token = data.token;
                localStorage.setItem('authToken', this.token);
                this.getUserInfo();
            } else {
                // No session token available
                this.user = null;
                this.updateUI();
            }
        } catch (error) {
            console.error('Error getting token from session:', error);
            this.user = null;
            this.updateUI();
        }
    }

    async getUserInfo() {
        try {
            const response = await fetch('/api/v1/auth/me', {
                headers: {
                    'Authorization': `Bearer ${this.token}`,
                    'Content-Type': 'application/json'
                }
            });
            
            if (response.ok) {
                const userData = await response.json();
                this.user = userData;
                this.updateUI();
            } else {
                // Token is invalid, clear it
                this.logout();
            }
        } catch (error) {
            console.error('Error getting user info:', error);
            this.logout();
        }
    }

    updateUI() {
        // Update navigation based on user state
        const userMenus = document.querySelectorAll('.navbar-nav');
        
        userMenus.forEach(menu => {
            // Check if this is a user menu by looking for specific elements
            const hasUserDropdown = menu.querySelector('.dropdown-toggle');
            const hasLogoutButton = menu.querySelector('button[type="submit"][form*="logout"]') ||
                                  menu.querySelector('a[href*="logout"]');
            const hasLoginRegister = menu.querySelector('a[href*="login"]') || 
                                   menu.querySelector('a[href*="register"]');
            
            // Determine menu type more reliably
            let isUserMenu = false;
            if (hasUserDropdown || hasLogoutButton) {
                isUserMenu = true;
            } else if (hasLoginRegister) {
                isUserMenu = false;
            } else {
                // Fallback: check if menu contains user-related text
                const menuText = menu.textContent.toLowerCase();
                if (menuText.includes('профиль') || menuText.includes('билеты') || menuText.includes('выйти')) {
                    isUserMenu = true;
                } else if (menuText.includes('войти') || menuText.includes('регистрация')) {
                    isUserMenu = false;
                }
            }
            
            if (isUserMenu) {
                // This is a user menu
                if (this.user) {
                    menu.style.display = 'block';
                    
                    // Update user name in dropdown
                    const userNameSpan = menu.querySelector('span[th\\:text*="user.firstName"]') || 
                                       menu.querySelector('span');
                    if (userNameSpan && this.user.firstName && this.user.lastName) {
                        userNameSpan.textContent = `${this.user.firstName} ${this.user.lastName}`;
                    }
                } else {
                    menu.style.display = 'none';
                }
            } else {
                // This is a guest menu (login/register buttons)
                if (this.user) {
                    menu.style.display = 'none';
                } else {
                    menu.style.display = 'block';
                }
            }
        });
    }

    async login(email, password) {
        try {
            const response = await fetch('/api/v1/auth/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                this.token = data.token;
                this.user = { id: data.userId, email: email };
                
                // Store token in localStorage
                localStorage.setItem('authToken', this.token);
                
                // Get full user info
                await this.getUserInfo();
                
                return { success: true, message: 'Успешный вход!' };
            } else {
                const error = await response.json();
                return { success: false, message: error.message || 'Ошибка входа' };
            }
        } catch (error) {
            console.error('Login error:', error);
            return { success: false, message: 'Произошла ошибка при входе' };
        }
    }

    async register(userData) {
        try {
            const response = await fetch('/api/v1/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (response.ok) {
                const data = await response.json();
                this.token = data.token;
                this.user = { id: data.userId, email: userData.email };
                
                // Store token in localStorage
                localStorage.setItem('authToken', this.token);
                
                // Get full user info
                await this.getUserInfo();
                
                return { success: true, message: 'Регистрация успешна!' };
            } else {
                const error = await response.json();
                return { success: false, message: error.message || 'Ошибка регистрации' };
            }
        } catch (error) {
            console.error('Registration error:', error);
            return { success: false, message: 'Произошла ошибка при регистрации' };
        }
    }

    logout() {
        this.user = null;
        this.token = null;
        localStorage.removeItem('authToken');
        this.updateUI();
        
        // Redirect to home page
        window.location.href = '/';
    }

    setupEventListeners() {
        // Login form submission - DISABLED for web login
        // Let Spring Security handle the form submission
        const loginForm = document.getElementById('loginForm');
        if (loginForm) {
            console.log('Login form found, but JavaScript handling is disabled for web login');
            // Remove the event listener to let Spring Security handle it
            // loginForm.addEventListener('submit', async (e) => {
            //     e.preventDefault();
            //     // ... rest of the code
            // });
        }

        // Registration form submission
        const registerForm = document.getElementById('registerForm');
        if (registerForm) {
            registerForm.addEventListener('submit', async (e) => {
                e.preventDefault();
                const formData = new FormData(registerForm);
                const userData = {
                    firstName: formData.get('firstName'),
                    lastName: formData.get('lastName'),
                    userName: formData.get('userName'),
                    email: formData.get('email'),
                    password: formData.get('password'),
                    role: formData.get('role') || 'USER'
                };
                
                const result = await this.register(userData);
                if (result.success) {
                    showNotification(result.message, 'success');
                    // Update UI immediately without redirect
                    this.updateUI();
                    // Optional: redirect after a longer delay to let user see the success message
                    setTimeout(() => {
                        window.location.href = '/';
                    }, 2000);
                } else {
                    showNotification(result.message, 'danger');
                }
            });
        }

        // Logout button
        const logoutButtons = document.querySelectorAll('button[type="submit"][form*="logout"]');
        logoutButtons.forEach(button => {
            button.addEventListener('click', (e) => {
                e.preventDefault();
                this.logout();
            });
        });
    }
}

// Initialize user state manager
const userState = new UserStateManager();

document.addEventListener('DOMContentLoaded', function() {
    // Initialize Bootstrap components
    initializeBootstrapComponents();
    
    // Initialize custom features
    initializeCustomFeatures();
    
    // Initialize animations
    initializeAnimations();
});

// Initialize Bootstrap components
function initializeBootstrapComponents() {
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl, {
            trigger: 'hover focus',
            delay: { show: 500, hide: 100 }
        });
    });

    // Initialize popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl, {
            trigger: 'hover focus',
            delay: { show: 500, hide: 100 }
        });
    });

    // Initialize modals
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        modal.addEventListener('show.bs.modal', function (event) {
            // Add animation class
            this.classList.add('animate-fadeInUp');
        });
        
        modal.addEventListener('hidden.bs.modal', function (event) {
            // Remove animation class
            this.classList.remove('animate-fadeInUp');
        });
    });

    // Initialize dropdowns
    const dropdowns = document.querySelectorAll('.dropdown-toggle');
    dropdowns.forEach(dropdown => {
        dropdown.addEventListener('show.bs.dropdown', function (event) {
            this.classList.add('animate-fadeInUp');
        });
    });

    // Initialize collapse
    const collapses = document.querySelectorAll('.collapse');
    collapses.forEach(collapse => {
        collapse.addEventListener('show.bs.collapse', function (event) {
            this.classList.add('animate-fadeInUp');
        });
    });
}

// Initialize custom features
function initializeCustomFeatures() {
    // Auto-hide alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(function(alert) {
        setTimeout(function() {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }, 5000);
    });

    // Form validation
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    });

    // Password strength indicator
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
        passwordInput.addEventListener('input', function() {
            const password = this.value;
            const strength = getPasswordStrength(password);
            updatePasswordStrengthIndicator(strength);
        });
    }

    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });

    // Initialize favorite buttons
    initializeFavoriteButtons();
    
    // Initialize cart functionality
    initializeCartFunctionality();
    
    // Initialize search functionality
    initializeSearchFunctionality();
}

// Initialize animations
function initializeAnimations() {
    // Add animation classes to elements when they come into view
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('animate-fadeInUp');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    // Observe cards and other elements
    document.querySelectorAll('.card, .feature-icon, .btn').forEach(el => {
        observer.observe(el);
    });
}

// Initialize favorite buttons
function initializeFavoriteButtons() {
    const favoriteButtons = document.querySelectorAll('.favorite-btn');
    favoriteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            
            const itemId = this.dataset.itemId;
            const itemType = this.dataset.itemType;
            const icon = this.querySelector('i');
            
            // Toggle visual state
            if (this.classList.contains('favorited')) {
                this.classList.remove('favorited');
                icon.classList.remove('fas');
                icon.classList.add('far');
                this.classList.remove('btn-danger');
                this.classList.add('btn-light');
            } else {
                this.classList.add('favorited');
                icon.classList.remove('far');
                icon.classList.add('fas');
                this.classList.remove('btn-light');
                this.classList.add('btn-danger');
            }
            
            // Add animation
            this.classList.add('animate-bounce');
            setTimeout(() => {
                this.classList.remove('animate-bounce');
            }, 1000);
            
            // TODO: Send AJAX request to toggle favorite
            console.log(`Toggling favorite for ${itemType} with ID ${itemId}`);
        });
    });
}

// Initialize cart functionality
function initializeCartFunctionality() {
    const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            
            const afishaId = this.dataset.afishaId;
            const originalText = this.innerHTML;
            
            // Show loading state
            this.innerHTML = '<span class="loading-spinner"></span> Добавление...';
            this.disabled = true;
            
            // Add animation
            this.classList.add('animate-pulse');
            
            // TODO: Send AJAX request to add to cart
            setTimeout(() => {
                this.innerHTML = '<i class="fas fa-check me-2"></i>Добавлено!';
                this.classList.remove('animate-pulse');
                this.classList.add('btn-success');
                
                setTimeout(() => {
                    this.innerHTML = originalText;
                    this.disabled = false;
                    this.classList.remove('btn-success');
                }, 2000);
            }, 1000);
            
            console.log(`Adding to cart: afisha ID ${afishaId}`);
        });
    });
}

// Initialize search functionality
function initializeSearchFunctionality() {
    const searchInputs = document.querySelectorAll('input[type="search"], input[name="search"]');
    searchInputs.forEach(input => {
        let searchTimeout;
        
        input.addEventListener('input', function() {
            clearTimeout(searchTimeout);
            searchTimeout = setTimeout(() => {
                // Add loading indicator
                const form = this.closest('form');
                const submitButton = form.querySelector('button[type="submit"]');
                if (submitButton) {
                    const originalText = submitButton.innerHTML;
                    submitButton.innerHTML = '<span class="loading-spinner"></span> Поиск...';
                    
                    setTimeout(() => {
                        submitButton.innerHTML = originalText;
                    }, 500);
                }
                
                // TODO: Implement live search functionality
                console.log('Searching for:', this.value);
            }, 300);
        });
    });
}

// Password strength calculation
function getPasswordStrength(password) {
    let strength = 0;
    
    if (password.length >= 8) strength++;
    if (password.match(/[a-z]/)) strength++;
    if (password.match(/[A-Z]/)) strength++;
    if (password.match(/[0-9]/)) strength++;
    if (password.match(/[^a-zA-Z0-9]/)) strength++;
    
    return strength;
}

// Update password strength indicator
function updatePasswordStrengthIndicator(strength) {
    const indicator = document.getElementById('passwordStrength');
    if (!indicator) return;
    
    const strengthText = ['Очень слабый', 'Слабый', 'Средний', 'Хороший', 'Отличный'][strength - 1] || 'Очень слабый';
    const strengthClass = ['danger', 'warning', 'info', 'success', 'success'][strength - 1] || 'danger';
    
    indicator.textContent = strengthText;
    indicator.className = `badge bg-${strengthClass}`;
}

// Utility functions
function showLoading(element) {
    element.innerHTML = '<span class="loading"></span> Загрузка...';
    element.disabled = true;
}

function hideLoading(element, originalText) {
    element.innerHTML = originalText;
    element.disabled = false;
}

// AJAX form submission
function submitForm(form, successCallback, errorCallback) {
    const formData = new FormData(form);
    const submitButton = form.querySelector('button[type="submit"]');
    const originalText = submitButton.innerHTML;
    
    showLoading(submitButton);
    
    fetch(form.action, {
        method: 'POST',
        body: formData,
        headers: {
            'X-Requested-With': 'XMLHttpRequest'
        }
    })
    .then(response => response.json())
    .then(data => {
        hideLoading(submitButton, originalText);
        if (data.success) {
            if (successCallback) successCallback(data);
        } else {
            if (errorCallback) errorCallback(data);
        }
    })
    .catch(error => {
        hideLoading(submitButton, originalText);
        console.error('Error:', error);
        if (errorCallback) errorCallback({message: 'Произошла ошибка при отправке формы'});
    });
}

// Show notification
function showNotification(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed`;
    alertDiv.style.cssText = 'top: 20px; right: 20px; z-index: 9999; min-width: 300px;';
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    document.body.appendChild(alertDiv);
    
    // Auto-remove after 5 seconds
    setTimeout(() => {
        if (alertDiv.parentNode) {
            alertDiv.parentNode.removeChild(alertDiv);
        }
    }, 5000);
}

// Format date
function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('ru-RU', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
}

// Format currency
function formatCurrency(amount) {
    return new Intl.NumberFormat('ru-RU', {
        style: 'currency',
        currency: 'KZT'
    }).format(amount);
}