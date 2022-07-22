package com.example.video3.model

data class ListRoomsData(
    val active_session_id: Any,
    val created_at: String,
    val enable_recording: Boolean,
    val id: String,
    val max_participants: Int,
    val record_type: String,
    val unique_name: String,
    val updated_at: String,
    val video_codecs: List<String>,
    val webhook_event_failover_url: String,
    val webhook_event_url: Any,
    val webhook_timeout_secs: Any
)