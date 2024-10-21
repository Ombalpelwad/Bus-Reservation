def get_user_info():
    name = input("What is your name? ")
    age = int(input("How old are you? "))
    return name, age

def greet_user(name, age):
    if age < 18:
        print(f"Hello, {name}! You're a minor.")
    elif 18 <= age < 65:
        print(f"Hello, {name}! You're an adult.")
    else:
        print(f"Hello, {name}! You're a senior citizen.")

def main():
    name, age = get_user_info()
    greet_user(name, age)

if __name__ == "__main__":
    main()
