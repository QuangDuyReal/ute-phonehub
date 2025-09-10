# Backend Dockerfile for Railway - Optimized for Tomcat 11
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy POM first for better dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests -B

# Production stage with Tomcat 11 for Jakarta EE
FROM tomcat:11.0-jdk17-openjdk-slim

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Remove default webapps and setup directory
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file as ROOT.war
COPY --from=build /app/target/ute-phonehub.war /usr/local/tomcat/webapps/ROOT.war

# Set environment variables for Railway
ENV CATALINA_OPTS="-Xmx512m -Xms256m"

# Expose port (Railway sẽ tự động map)
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/ || exit 1

# Start Tomcat
CMD ["catalina.sh", "run"]
