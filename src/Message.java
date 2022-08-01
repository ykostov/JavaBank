public enum Message {

    WELCOME {
     public String toString() {
            return "Welcome to JavaBank! You can login or register or exit";
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
            return ANSI_BLUE + "Welcome to our Main Menu, " + Extranet.getCurrentUserName() + "!." + ANSI_RESET + "\r\n" +  "Options you have: " + ANSI_GREEN + "account" + ANSI_RESET + " ; " + ANSI_GREEN + "Add Money" + ANSI_RESET + " in ATM ; " + ANSI_GREEN + "Exchange" + ANSI_RESET + " money in ATM, or " + ANSI_GREEN + "exit" + ANSI_RESET;
        }
    },
    HOWMUCH {
        public String toString() {
            return "how much money would you like to insert into the ATM?";
        }
    },
    CURRENTMONEYINATM {
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

    },
    USERNAMEREQ {
        public String toString() {
            return "Username must be 5-20 symbols";
        }

    },
    PASSREQ {
        public String toString() {
            return "password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20";
        }

    },
    ENTERCURR {
        public String toString() {
            return "Enter currency - BGN or  RSD";
        }

    },
    NOACCOUNT {
        public String toString() {
            return "You have selected an account you do not have!";
        }

    },
    WHERETODOOPERATION {
        public String toString() {
            return "In which account do you want to do this operation? (1 for acc1, 2 for acc2 or 3 for acc3";
        }
    },
    THECHOSENACCHAVE {
        public String toString() {
            return "The chosen account have:";
        }

    },
    ENTERMONEY {
        public String toString() {
            return "Please, enter how much money do you want";
        }

    },
    MOREMONEY {
        public String toString() {
            return "You have selected more money that you have in ATM/Account";
        }

    },
    SUCCESSOPERATION {
        public String toString() {
            return "Operation was successful";
        }

    },
    WRONG {
        public String toString() {
            return "Something went wrong";
        }

    },
    BIGERROR {
        public String toString() {
            return "You either don't have money in your ATM, or the currency of it and of the account does not matches. You can choose different account, import money in ATM with the right currency or change currency of account";
        }

    },
    BLOCKEDACC {
        public String toString() {
            return "your account is BLOCKED! Please, contact with admin to fix this. :(";
        }

    },
    HOWMUCHTOWITHDRAW {
        public String toString() {
            return "how much do you want to withdraw?";
        }

    };
}
