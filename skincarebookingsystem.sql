USE [skincare_booking]
GO
/****** Object:  Table [dbo].[Appointment]    Script Date: 4/8/2025 8:59:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Appointment](
	[AppointmentID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[AppointmentDate] [datetime] NOT NULL,
	[ServiceName] [nvarchar](100) NOT NULL,
	[Notes] [nvarchar](255) NOT NULL,
	[Status] [nvarchar](50) NULL,
	[AppointmentTime] [time](7) NULL,
 CONSTRAINT [PK_Appointments] PRIMARY KEY CLUSTERED 
(
	[AppointmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 4/8/2025 8:59:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](50) NOT NULL,
	[PasswordHash] [nvarchar](255) NOT NULL,
	[Email] [nvarchar](100) NULL,
	[Fullname] [nvarchar](100) NULL,
	[PhoneNumber] [nvarchar](15) NULL,
	[Role] [nvarchar](20) NOT NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Appointment] ON 

INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (1, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'a', N'a', NULL, NULL)
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (2, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'a', N'a', NULL, NULL)
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (3, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'a', N'a', NULL, NULL)
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (4, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'a', N'a', NULL, NULL)
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (5, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'a', N'a', NULL, NULL)
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (6, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'a', N'a', NULL, NULL)
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (7, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'cggfxkigul', N'fxdfkughvjbl', NULL, CAST(N'18:10:57.2833333' AS Time))
INSERT [dbo].[Appointment] ([AppointmentID], [UserID], [AppointmentDate], [ServiceName], [Notes], [Status], [AppointmentTime]) VALUES (8, 10, CAST(N'2025-04-08T00:00:00.000' AS DateTime), N'Triet long', N'Goi vinh vien', NULL, CAST(N'20:30:00' AS Time))
SET IDENTITY_INSERT [dbo].[Appointment] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (1, N'admin', N'hashed_password_1', N'admin@example.com', N'Administrator', N'0123456789', N'Admin')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (2, N'user1', N'hashed_password_2', N'user1@example.com', N'User One', N'0987654321', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (3, N'user2', N'hashed_password_3', N'user2@example.com', N'User Two', N'0912345678', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (4, N'admin', N'hashed_password_1', N'admin@example.com', N'Administrator', N'0123456789', N'Admin')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (5, N'user1', N'hashed_password_2', N'user1@example.com', N'User One', N'0987654321', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (6, N'user2', N'hashed_password_3', N'user2@example.com', N'User Two', N'0912345678', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (7, N'admin', N'hashed_password_1', N'admin@example.com', N'Administrator', N'0123456789', N'Admin')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (8, N'user1', N'hashed_password_2', N'user1@example.com', N'User One', N'0987654321', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (9, N'user2', N'hashed_password_3', N'user2@example.com', N'User Two', N'0912345678', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (10, N'nguyenvana', N'123456', N'a@example.com', N'Nguyen Van A', N'0123456789', N'User')
INSERT [dbo].[Users] ([UserID], [Username], [PasswordHash], [Email], [Fullname], [PhoneNumber], [Role]) VALUES (11, N'tranthib', N'abcdef', N'b@example.com', N'Tran Thi B', N'0987654321', N'Admin')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
