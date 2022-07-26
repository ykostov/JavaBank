public enum Message {
    WELCOME {
     public String toString() {
            return "Welcome to JavaBank! You can login or register";
        }
    },

    BADINPUT {
    public String toString() {
            return "Got bad input. Please try again or enter 'exit'";
        }
    },
    USERNAME {
        public String toString() {
            return "Enter username: ";
        }
    },
    PASSWD {
        public String toString() {
            return "Enter password: ";
        }
    },
    EMAIL {
        public String toString() {
            return "Enter email: ";
        }
    },
    GOBACK {
        public String toString() {
            return "Enter 'back' to go back or exit to exit";
        }
    },
    SUCCESSLOGIN {
        public String toString() {
            return "You have logged in successfully!";
        }
    },
    NOSUCCESSLOGIN {
        public String toString() {
            return "Incorrect password, try again or enter 'stop'";
        }
    },
    TAKEN {
        public String toString() {
            return "Username is already taken, try again";
        }
    },
    SUCCESSREGISTER {
        public String toString() {
            return "You have registered successfully!";
        }
    },
    MAINMENU {
        public String toString() {
            return "type 'new account' to create an account or 'add money' to add money in ATM (obviously)";
        }
    },
    HOWMUCH {
        public String toString() {
            return "how much money would you like to insert into the ATM?";
        }
    },
    CURRENTMONEY {
        public String toString() {
            return "current money in ATM: ";
        }
    };
}
