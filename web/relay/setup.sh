#!/bin/bash
# Exiled Kingdoms relay: one-shot server setup (deobf/RELAY_SPEC.md). Run as root by the VM's cloud-init.
set -e
PORT=32200
URL=https://knightdx91-alt.github.io/Exiled-kingdoms/relay/ekrelay.py
mkdir -p /opt/ekrelay
curl -fsSL "$URL" -o /opt/ekrelay/ekrelay.py
command -v python3 >/dev/null || { (dnf -y install python3 || yum -y install python3 || apt-get -y install python3) ; }
PY=$(command -v python3)
cat > /etc/systemd/system/ekrelay.service <<UNIT
[Unit]
Description=Exiled Kingdoms relay
After=network-online.target

[Service]
ExecStart=$PY /opt/ekrelay/ekrelay.py
Restart=always
RestartSec=3
User=nobody
Environment=EKR_PORT=$PORT

[Install]
WantedBy=multi-user.target
UNIT
# the VM's own firewall (the cloud Security List rule is added in the console)
if command -v firewall-cmd >/dev/null 2>&1; then
  firewall-cmd --permanent --add-port=$PORT/tcp || true
  firewall-cmd --reload || true
fi
if command -v iptables >/dev/null 2>&1; then
  iptables -C INPUT -p tcp --dport $PORT -j ACCEPT 2>/dev/null || iptables -I INPUT 1 -p tcp --dport $PORT -j ACCEPT
  command -v netfilter-persistent >/dev/null 2>&1 && netfilter-persistent save || true
fi
systemctl daemon-reload
systemctl enable --now ekrelay
echo "EK relay installed on port $PORT"
