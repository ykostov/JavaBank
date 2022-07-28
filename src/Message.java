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
            return "Incorrect password, try again or enter 'exit' to exit";
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
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public String toString() {
            return ANSI_BLUE + "Welcome to our Main Menu, " + Extranet.getCurrentUserName() + "!." + ANSI_RESET + "\r\n" +  "Options you have: Open an " + ANSI_GREEN + "account" + ANSI_RESET + " ; " + ANSI_GREEN + "Add Money" + ANSI_RESET + " in ATM ; " + ANSI_GREEN + "Exchange" + ANSI_RESET + " money in ATM, or " + ANSI_GREEN + "exit" + ANSI_RESET;
        }
    },
    HOWMUCH {
        public String toString() {
            return "how much money would you like to insert into the ATM?";
        }
    },
    CURRENTMONEY {
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_RESET = "\u001B[0m";
        public String toString() {
            return ANSI_BLUE + "You currently have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. " + ANSI_RESET;
        }
    },
    MAXACC {
        public String toString() {
            return "you have maximum accounts allowed for a basic user (3). Become VIP to get more";
        }
    },
    ACCMENU {
        public String toString() {
            return "- that is your number of accounts. Would you like to 'list' them, 'create' a new one, 'import' money, 'withdraw' money or 'exit'?";
        }

    },
    SUCCESSACC {
        public String toString() {
            return "Account created successfully!";
        }

    };
}
