require 'websocket-eventmachine-client'

EM.run do

  ws = WebSocket::EventMachine::Client.connect(:uri => 'ws://localhost:9000/proxy')

  ws.onopen do
    puts "Connected"
  end

  ws.onmessage do |msg, type|
    puts "Received message: Length: #{msg.length} Type: #{type}"
  end

  ws.onclose do |code, reason|
    puts "Disconnected with status code: #{code} #{reason.to_s}"
  end

  EventMachine.add_periodic_timer(1) do
    # ws.send "p" * 130000
    ws.send ("p" * 123980).bytes, :type => :binary
  end

end
