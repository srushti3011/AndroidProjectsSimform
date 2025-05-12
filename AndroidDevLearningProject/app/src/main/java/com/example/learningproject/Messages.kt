package com.example.learningproject

data class MessageItem(
    val type: MessageType,
    val content: String,
    val time: String
)

data class MessageCollection(
    val date: String,
    val messages: Array<MessageItem>
)

enum class MessageType {
    Received, Sent
}

val messages = arrayOf(
    MessageCollection(
        "15/05/25",
        arrayOf(
            MessageItem(
                type = MessageType.Sent,
                content = "Hello",
                time = "09:08"
            ),
            MessageItem(
                type = MessageType.Received,
                content = "Hello",
                time = "09:09"
            ),
            MessageItem(
                type = MessageType.Received,
                content = "Good morning. Just checking in regarding the client presentation. " +
                        "Please let me know when you are done with it so that we can discuss " +
                        "further. We need to finalize the details.",
                time = "09:10"
            )
        )
    ),
    MessageCollection(
        "16/05/25",
        arrayOf(
            MessageItem(
                type = MessageType.Sent,
                content = "Good morning. Yes, the slides are finalized and ready for review. " +
                        "I’ve also added the latest data points and revised some sections for " +
                        "clarity. Please have a look and let me know if there are any additional " +
                        "changes required before we finalize everything.",
                time = "09:12"
            ),
            MessageItem(
                type = MessageType.Received,
                content = "That's excellent. Would it be possible to get a copy before our " +
                        "meeting at 11? I’d like to go over the slides in detail to ensure we’re " +
                        "all on the same page.",
                time = "09:14"
            ),
            MessageItem(
                type = MessageType.Sent,
                content = "Absolutely. I’ll send it over within the next 15 minutes. I’m currently " +
                        "finalizing the last few points, and once that’s done, I’ll send the final " +
                        "version your way. Expect it in your inbox shortly so that you have plenty " +
                        "of time to review before our meeting.",
                time = "09:15"
            )
        )
    ),
    MessageCollection(
        "17/05/25",
        arrayOf(
            MessageItem(
            type = MessageType.Received,
            content = "Appreciate it. Let me know if you need anything from my side. I’ll make " +
                    "sure to be available if you have any last-minute questions or adjustments " +
                    "you need to discuss before the meeting. If there’s anything specific you " +
                    "want me to check, feel free to send it my way.",
            time = "09:16"
        ),
        MessageItem(
            type = MessageType.Sent,
            content = "Will do. I’ll also include a short summary for quick reference. The " +
                    "summary will highlight the key points, and I’ll ensure that everything " +
                    "is clear so that you can go over the main topics easily. Let me know if " +
                    "you'd prefer a more detailed overview or just a high-level summary.",
            time = "09:18"
        ),
        MessageItem(
            type = MessageType.Received,
            content = "Perfect. That will be very helpful for the initial discussion. I want " +
                    "to make sure the meeting goes smoothly, and having a quick summary will " +
                    "definitely help streamline things. Once I go through the slides, I’ll be " +
                    "able to provide more focused feedback and suggestions during our session.",
            time = "09:20"
        ),
        MessageItem(
            type = MessageType.Sent,
            content = "Looking forward to a productive session. I’m confident we’ll make good " +
                    "progress, and it will be a great opportunity to align our next steps. " +
                    "Let’s make sure to cover everything in detail, so we’re fully prepared " +
                    "for the client meeting. I’m sure this will be a fruitful discussion.",
            time = "09:22"
        ),
        MessageItem(
            type = MessageType.Sent,
            content = "See you at 11. I’ll be ready to discuss the slides and finalize any " +
                    "remaining details before our meeting with the client. I’ll also make " +
                    "sure to have all the necessary materials and information ready, so we " +
                    "can go through everything efficiently.",
            time = "09:22"
        ),
        MessageItem(
            type = MessageType.Received,
            content = "See you then. Thanks again. I really appreciate the effort you’ve put " +
                    "into the presentation.",
            time = "09:23"
        ),
        MessageItem(
            type = MessageType.Sent,
            content = "You're most welcome. I’m happy to help. Let’s make sure everything is " +
                    "perfect, and if you need any further adjustments or additions, don’t " +
                    "hesitate to reach out. Looking forward to our meeting and to a successful " +
                    "presentation.",
            time = "09:24"
        )
        )
    )
)