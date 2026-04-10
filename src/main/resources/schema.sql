CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_role ENUM('Admin', 'SuperUser', 'Member'),
    user_phone VARCHAR(20)
);

CREATE TABLE cats (
    cat_id INT AUTO_INCREMENT PRIMARY KEY,
    cat_owner_id INT,
    cat_name VARCHAR(50) NOT NULL,
    cat_birthday DATE NOT NULL,
    cat_sex ENUM('Male', 'Female') NOT NULL,
    cat_color ENUM ('Blue', 'Lilac', 'Red', 'Cream', 'Cinnamon', 'Chocolate') NOT NULL,
    cat_fertile BOOL NOT NULL,
    cat_alive BOOL NOT NULL,
    cat_image_path VARCHAR(50),
    cat_pedigree_path VARCHAR(50),
    FOREIGN KEY (cat_owner_id) REFERENCES users(user_id)
);

CREATE TABLE users_cats (
    user_id INT NOT NULL,
    cat_id INT NOT NULL,
    PRIMARY KEY (user_id, cat_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (cat_id) REFERENCES cats(cat_id)
);