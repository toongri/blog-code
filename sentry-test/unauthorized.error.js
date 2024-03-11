class UnauthorizedError extends Error {

    constructor() {
        super();
        this.name = "UnauthorizedError";
    }
}

module.exports = UnauthorizedError;