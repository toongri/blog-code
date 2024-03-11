class BadRequestError extends Error {

    constructor() {
        super();
        this.name = "BadRequestError";
    }
}

module.exports = BadRequestError;