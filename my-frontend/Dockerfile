# Этап сборки
FROM node:alpine AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json (если есть)
COPY package.json package-lock.json /app/

# Устанавливаем зависимости
RUN npm install

# Копируем исходный код
COPY . /app

# Строим приложение для продакшн
RUN npm run build

# Этап для продакшн
FROM node:alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем только что собранные файлы из этапа сборки
COPY --from=build /app/dist /app/dist

# Устанавливаем http-server для сервировки файлов
RUN npm install -g http-server

# Открываем порт 8080
EXPOSE 8080

# Запускаем сервер для обслуживания приложения
CMD ["http-server", "dist", "-p", "8081"]