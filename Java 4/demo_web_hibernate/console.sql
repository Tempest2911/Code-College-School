CREATE TABLE Cupcake (
                         CupcakeID INT PRIMARY KEY IDENTITY(1,1),
                         Flavor NVARCHAR(50) NOT NULL,
                         Frosting NVARCHAR(50),
                         Price DECIMAL(5,2) NOT NULL,
                         IsGlutenFree BIT DEFAULT 0,
                         BakedOn DATE
);

INSERT INTO Cupcake (Flavor, Frosting, Price, IsGlutenFree, BakedOn) VALUES
                                                                         ('Vanilla', 'Chocolate', 2.50, 0, '2025-07-15'),
                                                                         ('Red Velvet', 'Cream Cheese', 3.00, 0, '2025-07-16'),
                                                                         ('Lemon', 'Lemon', 2.75, 1, '2025-07-14');

CREATE TABLE RetroGame (
                           GameID INT PRIMARY KEY IDENTITY(1,1),
                           Title NVARCHAR(100) NOT NULL,
                           Console NVARCHAR(50),
                           ReleaseYear INT,
                           IsMultiplayer BIT DEFAULT 0,
                           Rating FLOAT
);

INSERT INTO RetroGame (Title, Console, ReleaseYear, IsMultiplayer, Rating) VALUES
                                                                               ('Super Mario Bros', 'NES', 1985, 1, 9.5),
                                                                               ('Tetris', 'Game Boy', 1989, 0, 8.9),
                                                                               ('Street Fighter II', 'SNES', 1991, 1, 9.2);

CREATE TABLE TalentPet (
                           PetID INT PRIMARY KEY IDENTITY(1,1),
                           Name NVARCHAR(50),
                           Species NVARCHAR(50),
                           TrickPerformed NVARCHAR(100),
                           Score INT,
                           IsFinalist BIT DEFAULT 0
);

INSERT INTO TalentPet (Name, Species, TrickPerformed, Score, IsFinalist) VALUES
                                                                             ('Mr. Wiggles', 'Dog', 'Backflip', 85, 1),
                                                                             ('Whiskertron', 'Cat', 'Keyboard Solo', 90, 1),
                                                                             ('Flappy', 'Parrot', 'Math Counting', 75, 0);





