class InternalError extends Error {

    constructor() {
        super();
        this.name = "InternalError";

    }
}

module.exports = InternalError;